#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
住宅ローン計算プログラム。

年収900万円、金遣いが荒い人の場合の購入可能なマンション価格を計算します。
元利均等返済方式を使用し、フルローン（頭金なし）を前提としています。

@author 作成者名
@version 1.0
@since 1.0
"""

import locale

# 定数
ANNUAL_INCOME = 9_000_000  # 年収900万円
REPAYMENT_RATIO_CONSERVATIVE = 0.25  # 金遣いが荒い場合の返済比率（25%）
REPAYMENT_RATIO_NORMAL = 0.30  # 通常の返済比率（30%）
INTEREST_RATE = 0.01  # 金利1.0%（年利）
REPAYMENT_YEARS = 35  # 返済期間35年
DOWN_PAYMENT_RATIO = 0.0  # 頭金比率0%（フルローン）


def main():
    """メイン処理"""
    print("==========================================")
    print("住宅ローン計算プログラム")
    print("年収900万円、金遣いが荒い人の場合")
    print("【フルローン（頭金なし）】")
    print("==========================================\n")
    
    # 基本情報の表示
    display_basic_info()
    
    # 返済可能額の計算
    annual_repayment_capacity = calculate_annual_repayment_capacity()
    monthly_repayment_capacity = annual_repayment_capacity / 12
    
    print("\n【返済可能額の計算】")
    print(f"年収: {format_currency(ANNUAL_INCOME)}")
    print(f"返済比率（金遣いが荒い場合）: {REPAYMENT_RATIO_CONSERVATIVE * 100:.1f}%")
    print(f"年間返済可能額: {format_currency(annual_repayment_capacity)}")
    print(f"月々返済可能額: {format_currency(monthly_repayment_capacity)}")
    
    # 借入可能額の計算（元利均等返済）
    max_loan_amount = calculate_max_loan_amount(monthly_repayment_capacity)
    
    print("\n【借入可能額の計算】")
    print(f"金利: {INTEREST_RATE * 100:.2f}%（年利）")
    print(f"返済期間: {REPAYMENT_YEARS}年")
    print(f"最大借入可能額: {format_currency(max_loan_amount)}")
    
    # 購入可能なマンション価格の計算
    max_house_price = calculate_max_house_price(max_loan_amount)
    
    print("\n【購入可能なマンション価格（フルローン）】")
    print(f"頭金比率: {DOWN_PAYMENT_RATIO * 100:.0f}%（フルローン）")
    print(f"最大購入可能価格: {format_currency(max_house_price)}")
    print(f"  - 借入額: {format_currency(max_loan_amount)}（全額借入）")
    print(f"  - 頭金: {format_currency(max_house_price - max_loan_amount)}")
    
    # 実際の返済シミュレーション
    print("\n【返済シミュレーション】")
    simulate_repayment(max_loan_amount)
    
    # 比較：通常の返済比率の場合
    print("\n【参考：通常の返済比率（30%）の場合】")
    normal_annual_repayment = ANNUAL_INCOME * REPAYMENT_RATIO_NORMAL
    normal_monthly_repayment = normal_annual_repayment / 12
    normal_max_loan_amount = calculate_max_loan_amount(normal_monthly_repayment)
    normal_max_house_price = calculate_max_house_price(normal_max_loan_amount)
    print(f"年間返済可能額: {format_currency(normal_annual_repayment)}")
    print(f"最大購入可能価格: {format_currency(normal_max_house_price)}")
    print(f"差額: {format_currency(normal_max_house_price - max_house_price)}")


def display_basic_info():
    """
    基本情報を表示します。
    年収と金遣いの状況を標準出力に出力します。
    """
    print("【基本情報】")
    print(f"年収: {format_currency(ANNUAL_INCOME)}")
    print("金遣い: 荒い（生活費が高い）")
    print("→ 返済可能額を保守的に設定（年収の25%）")


def calculate_annual_repayment_capacity():
    """
    年間返済可能額を計算します。
    金遣いが荒い場合は保守的に年収の25%を返済可能額とします。
    
    Returns:
        float: 年間返済可能額（円）
    """
    return ANNUAL_INCOME * REPAYMENT_RATIO_CONSERVATIVE


def calculate_max_loan_amount(monthly_repayment):
    """
    月々の返済可能額から最大借入可能額を逆算します。
    元利均等返済の計算式を使用します。
    
    計算式：
    月返済額 = 借入額 × (月利) × (1 + 月利)^返済月数 / ((1 + 月利)^返済月数 - 1)
    
    これを逆算すると：
    借入額 = 月返済額 × ((1 + 月利)^返済月数 - 1) / (月利 × (1 + 月利)^返済月数)
    
    Args:
        monthly_repayment (float): 月々の返済可能額（円）
    
    Returns:
        float: 最大借入可能額（円）
    """
    monthly_interest_rate = INTEREST_RATE / 12  # 月利
    repayment_months = REPAYMENT_YEARS * 12  # 返済月数
    
    numerator = (1 + monthly_interest_rate) ** repayment_months - 1
    denominator = monthly_interest_rate * (1 + monthly_interest_rate) ** repayment_months
    
    return monthly_repayment * (numerator / denominator)


def calculate_max_house_price(loan_amount):
    """
    借入可能額から購入可能なマンション価格を計算します。
    フルローンの場合、マンション価格 = 借入額（頭金なし）となります。
    
    Args:
        loan_amount (float): 借入可能額（円）
    
    Returns:
        float: 購入可能なマンション価格（円）
    """
    # フルローンの場合、借入額 = マンション価格
    return loan_amount


def simulate_repayment(loan_amount):
    """
    返済シミュレーションを実行し、結果を標準出力に表示します。
    月々返済額、年間返済額、総返済額、支払利息総額、初年度の返済内訳を表示します。
    
    Args:
        loan_amount (float): 借入額（円）
    """
    monthly_interest_rate = INTEREST_RATE / 12
    repayment_months = REPAYMENT_YEARS * 12
    
    # 元利均等返済の月返済額を計算
    monthly_repayment = (loan_amount * monthly_interest_rate * 
                        (1 + monthly_interest_rate) ** repayment_months / 
                        ((1 + monthly_interest_rate) ** repayment_months - 1))
    
    print(f"借入額: {format_currency(loan_amount)}")
    print(f"月々返済額: {format_currency(monthly_repayment)}")
    print(f"年間返済額: {format_currency(monthly_repayment * 12)}")
    print(f"総返済額: {format_currency(monthly_repayment * repayment_months)}")
    print(f"支払利息総額: {format_currency(monthly_repayment * repayment_months - loan_amount)}")
    
    # 初年度と最終年度の返済内訳を表示
    print("\n【返済内訳（初年度）】")
    remaining_principal = loan_amount
    first_year_interest = 0
    first_year_principal = 0
    
    for i in range(12):
        interest = remaining_principal * monthly_interest_rate
        principal = monthly_repayment - interest
        first_year_interest += interest
        first_year_principal += principal
        remaining_principal -= principal
    
    print(f"利息: {format_currency(first_year_interest)}")
    print(f"元金: {format_currency(first_year_principal)}")
    print(f"合計: {format_currency(first_year_interest + first_year_principal)}")


def format_currency(amount):
    """
    数値を日本の通貨フォーマット（円）に変換します。
    
    例：1000000 → "¥1,000,000"
    
    Args:
        amount (float): フォーマットする金額
    
    Returns:
        str: フォーマットされた通貨文字列（例：¥1,000,000）
    """
    # ロケールを日本語に設定
    try:
        locale.setlocale(locale.LC_ALL, 'ja_JP.UTF-8')
    except locale.Error:
        # ロケール設定に失敗した場合は、手動でフォーマット
        return f"¥{amount:,.0f}"
    
    return locale.currency(amount, grouping=True)


if __name__ == "__main__":
    main()
