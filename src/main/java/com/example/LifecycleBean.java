package com.example;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class LifecycleBean {

    private UserFormDTO userForm = new UserFormDTO();
    private String result;
    private List<String> lifecycleLog = new ArrayList<>();

    // ライフサイクルログを取得（各フェーズの動作を記録）
    public List<String> getLifecycleLog() {
        return lifecycleLog;
    }

    // リクエスト値の適用フェーズをシミュレート
    public void logApplyRequestValues() {
        lifecycleLog.add("✓ Apply Request Values: HTTPパラメータがUIComponentに適用されました");
        if (userForm.getUserName() != null) {
            lifecycleLog.add("  → userName: " + userForm.getUserName());
        }
        if (userForm.getAge() != null) {
            lifecycleLog.add("  → age: " + userForm.getAge());
        }
    }

    // 検証フェーズのシミュレート（submit()内で実行）
    private void performValidations() {
        if (userForm.getAge() != null && (userForm.getAge() < 0 || userForm.getAge() > 150)) {
            FacesContext.getCurrentInstance().addMessage("age",
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "年齢は0から150の間で入力してください", null));
            lifecycleLog.add("✗ Process Validations: 年齢のバリデーションエラー");
        } else if (userForm.getAge() != null) {
            lifecycleLog.add("✓ Process Validations: 年齢のバリデーション成功");
        }
        
        if (userForm.getEmail() != null && !userForm.getEmail().isEmpty() && !userForm.getEmail().contains("@")) {
            FacesContext.getCurrentInstance().addMessage("email",
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "メールアドレスの形式が正しくありません", null));
            lifecycleLog.add("✗ Process Validations: メールアドレスのバリデーションエラー");
        } else if (userForm.getEmail() != null && !userForm.getEmail().isEmpty()) {
            lifecycleLog.add("✓ Process Validations: メールアドレスのバリデーション成功");
        }
    }

    // モデル更新フェーズをシミュレート
    public void logUpdateModelValues() {
        lifecycleLog.add("✓ Update Model Values: バリデーション通過後、Beanのプロパティに値が設定されました");
        lifecycleLog.add("  → userNameプロパティ: " + userForm.getUserName());
        lifecycleLog.add("  → ageプロパティ: " + userForm.getAge());
        lifecycleLog.add("  → emailプロパティ: " + userForm.getEmail());
    }

    // アプリケーション呼び出しフェーズ
    public String submit() {
        // 検証フェーズのシミュレート
        performValidations();
        
        // バリデーションエラーがある場合は処理を中断
        if (FacesContext.getCurrentInstance().getMessageList().stream()
            .anyMatch(m -> m.getSeverity() == FacesMessage.SEVERITY_ERROR)) {
            lifecycleLog.add("⚠ バリデーションエラーのため、Update Model Values以降はスキップされました");
            return null;
        }
        
        // モデル更新フェーズのログ
        logUpdateModelValues();
        
        // アプリケーション呼び出しフェーズ
        lifecycleLog.add("✓ Invoke Application: ビジネスロジックが実行されました");
        
        // ビジネスロジックの例
        if (userForm.getUserName() != null && !userForm.getUserName().isEmpty()) {
            result = "こんにちは、" + userForm.getUserName() + " さん！";
            if (userForm.getAge() != null) {
                result += " 年齢: " + userForm.getAge() + "歳";
            }
            if (userForm.getEmail() != null && !userForm.getEmail().isEmpty()) {
                result += " メール: " + userForm.getEmail();
            }
            lifecycleLog.add("  → 結果: " + result);
        }
        
        // ナビゲーションの例（nullを返すと同画面再表示）
        return null; // 同画面を再表示
    }

    // クリアメソッド
    public void clear() {
        userForm.clear();
        result = null;
        lifecycleLog.clear();
    }

    // Getter / Setter
    public UserFormDTO getUserForm() {
        return userForm;
    }

    public void setUserForm(UserFormDTO userForm) {
        this.userForm = userForm;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}

