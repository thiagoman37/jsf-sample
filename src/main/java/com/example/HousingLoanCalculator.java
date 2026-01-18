package com.example;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * 住宅ローン計算プログラム。
 * <p>
 * 年収900万円、金遣いが荒い人の場合の購入可能なマンション価格を計算します。
 * 元利均等返済方式を使用し、フルローン（頭金なし）を前提としています。
 * 
 * @author 作成者名
 * @version 1.0
 * @since 1.0
 */
public class HousingLoanCalculator {
    
    // 定数
    private static final double ANNUAL_INCOME = 9_000_000; // 年収900万円
    private static final double REPAYMENT_RATIO_CONSERVATIVE = 0.25; // 金遣いが荒い場合の返済比率（25%）
    private static final double REPAYMENT_RATIO_NORMAL = 0.30; // 通常の返済比率（30%）
    private static final double INTEREST_RATE = 0.01; // 金利1.0%（年利）
    private static final int REPAYMENT_YEARS = 35; // 返済期間35年
    private static final double DOWN_PAYMENT_RATIO = 0.0; // 頭金比率0%（フルローン）
    
    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("住宅ローン計算プログラム");
        System.out.println("年収900万円、金遣いが荒い人の場合");
        System.out.println("【フルローン（頭金なし）】");
        System.out.println("==========================================\n");
        
        // 基本情報の表示
        displayBasicInfo();
        
        // 返済可能額の計算
        double annualRepaymentCapacity = calculateAnnualRepaymentCapacity();
        double monthlyRepaymentCapacity = annualRepaymentCapacity / 12;
        
        System.out.println("\n【返済可能額の計算】");
        System.out.printf("年収: %s円\n", formatCurrency(ANNUAL_INCOME));
        System.out.printf("返済比率（金遣いが荒い場合）: %.1f%%\n", REPAYMENT_RATIO_CONSERVATIVE * 100);
        System.out.printf("年間返済可能額: %s円\n", formatCurrency(annualRepaymentCapacity));
        System.out.printf("月々返済可能額: %s円\n", formatCurrency(monthlyRepaymentCapacity));
        
        // 借入可能額の計算（元利均等返済）
        double maxLoanAmount = calculateMaxLoanAmount(monthlyRepaymentCapacity);
        
        System.out.println("\n【借入可能額の計算】");
        System.out.printf("金利: %.2f%%（年利）\n", INTEREST_RATE * 100);
        System.out.printf("返済期間: %d年\n", REPAYMENT_YEARS);
        System.out.printf("最大借入可能額: %s円\n", formatCurrency(maxLoanAmount));
        
        // 購入可能なマンション価格の計算
        double maxHousePrice = calculateMaxHousePrice(maxLoanAmount);
        
        System.out.println("\n【購入可能なマンション価格（フルローン）】");
        System.out.printf("頭金比率: %.0f%%（フルローン）\n", DOWN_PAYMENT_RATIO * 100);
        System.out.printf("最大購入可能価格: %s円\n", formatCurrency(maxHousePrice));
        System.out.printf("  - 借入額: %s円（全額借入）\n", formatCurrency(maxLoanAmount));
        System.out.printf("  - 頭金: %s円\n", formatCurrency(maxHousePrice - maxLoanAmount));
        
        // 実際の返済シミュレーション
        System.out.println("\n【返済シミュレーション】");
        simulateRepayment(maxLoanAmount);
        
        // 比較：通常の返済比率の場合
        System.out.println("\n【参考：通常の返済比率（30%）の場合】");
        double normalAnnualRepayment = ANNUAL_INCOME * REPAYMENT_RATIO_NORMAL;
        double normalMonthlyRepayment = normalAnnualRepayment / 12;
        double normalMaxLoanAmount = calculateMaxLoanAmount(normalMonthlyRepayment);
        double normalMaxHousePrice = calculateMaxHousePrice(normalMaxLoanAmount);
        System.out.printf("年間返済可能額: %s円\n", formatCurrency(normalAnnualRepayment));
        System.out.printf("最大購入可能価格: %s円\n", formatCurrency(normalMaxHousePrice));
        System.out.printf("差額: %s円\n", formatCurrency(normalMaxHousePrice - maxHousePrice));
    }
    
    /**
     * 基本情報を表示します。
     * 年収と金遣いの状況を標準出力に出力します。
     */
    private static void displayBasicInfo() {
        System.out.println("【基本情報】");
        System.out.printf("年収: %s円\n", formatCurrency(ANNUAL_INCOME));
        System.out.println("金遣い: 荒い（生活費が高い）");
        System.out.println("→ 返済可能額を保守的に設定（年収の25%）");
    }
    
    /**
     * 年間返済可能額を計算します。
     * 金遣いが荒い場合は保守的に年収の25%を返済可能額とします。
     * 
     * @return 年間返済可能額（円）
     */
    private static double calculateAnnualRepaymentCapacity() {
        return ANNUAL_INCOME * REPAYMENT_RATIO_CONSERVATIVE;
    }
    
    /**
     * 月々の返済可能額から最大借入可能額を逆算します。
     * 元利均等返済の計算式を使用します。
     * 
     * <p>計算式：
     * <pre>
     * 月返済額 = 借入額 × (月利) × (1 + 月利)^返済月数 / ((1 + 月利)^返済月数 - 1)
     * </pre>
     * 
     * <p>これを逆算すると：
     * <pre>
     * 借入額 = 月返済額 × ((1 + 月利)^返済月数 - 1) / (月利 × (1 + 月利)^返済月数)
     * </pre>
     * 
     * @param monthlyRepayment 月々の返済可能額（円）
     * @return 最大借入可能額（円）
     */
    private static double calculateMaxLoanAmount(double monthlyRepayment) {
        double monthlyInterestRate = INTEREST_RATE / 12; // 月利
        int repaymentMonths = REPAYMENT_YEARS * 12; // 返済月数
        
        double numerator = Math.pow(1 + monthlyInterestRate, repaymentMonths) - 1;
        double denominator = monthlyInterestRate * Math.pow(1 + monthlyInterestRate, repaymentMonths);
        
        return monthlyRepayment * (numerator / denominator);
    }
    
    /**
     * 借入可能額から購入可能なマンション価格を計算します。
     * フルローンの場合、マンション価格 = 借入額（頭金なし）となります。
     * 
     * @param loanAmount 借入可能額（円）
     * @return 購入可能なマンション価格（円）
     */
    private static double calculateMaxHousePrice(double loanAmount) {
        // フルローンの場合、借入額 = マンション価格
        return loanAmount;
    }
    
    /**
     * 返済シミュレーションを実行し、結果を標準出力に表示します。
     * 月々返済額、年間返済額、総返済額、支払利息総額、初年度の返済内訳を表示します。
     * 
     * @param loanAmount 借入額（円）
     */
    private static void simulateRepayment(double loanAmount) {
        double monthlyInterestRate = INTEREST_RATE / 12;
        int repaymentMonths = REPAYMENT_YEARS * 12;
        
        // 元利均等返済の月返済額を計算
        double monthlyRepayment = loanAmount * monthlyInterestRate * 
            Math.pow(1 + monthlyInterestRate, repaymentMonths) / 
            (Math.pow(1 + monthlyInterestRate, repaymentMonths) - 1);
        
        System.out.printf("借入額: %s円\n", formatCurrency(loanAmount));
        System.out.printf("月々返済額: %s円\n", formatCurrency(monthlyRepayment));
        System.out.printf("年間返済額: %s円\n", formatCurrency(monthlyRepayment * 12));
        System.out.printf("総返済額: %s円\n", formatCurrency(monthlyRepayment * repaymentMonths));
        System.out.printf("支払利息総額: %s円\n", 
            formatCurrency(monthlyRepayment * repaymentMonths - loanAmount));
        
        // 初年度と最終年度の返済内訳を表示
        System.out.println("\n【返済内訳（初年度）】");
        double remainingPrincipal = loanAmount;
        double firstYearInterest = 0;
        double firstYearPrincipal = 0;
        
        for (int i = 0; i < 12; i++) {
            double interest = remainingPrincipal * monthlyInterestRate;
            double principal = monthlyRepayment - interest;
            firstYearInterest += interest;
            firstYearPrincipal += principal;
            remainingPrincipal -= principal;
        }
        
        System.out.printf("利息: %s円\n", formatCurrency(firstYearInterest));
        System.out.printf("元金: %s円\n", formatCurrency(firstYearPrincipal));
        System.out.printf("合計: %s円\n", formatCurrency(firstYearInterest + firstYearPrincipal));
    }
    
    /**
     * 数値を日本の通貨フォーマット（円）に変換します。
     * 
     * <p>例：{@code 1000000} → {@code "¥1,000,000"}
     * 
     * @param amount フォーマットする金額
     * @return フォーマットされた通貨文字列（例：¥1,000,000）
     */
    private static String formatCurrency(double amount) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.JAPAN);
        return formatter.format(amount);
    }
    
    /* 
     * これは通常のブロックコメントです。
     * JavaDocには含まれません。
     * このメソッドはテスト用です。
     */
    private static void testMethodWithBlockComment() {
        // 何もしない
    }
    
    /**
     * これはJavaDocコメントです。
     * JavaDocに含まれます。
     * このメソッドはテスト用です。
     * 
     * @param value テスト用の値
     * @return 常に0を返す
     */
    private static int testMethodWithJavaDocComment(int value) {
        return 0;
    }
}
