# JSF Sample Project

JavaServer Faces (JSF) の学習用サンプルプロジェクトです。
JSFのライフサイクルや基本的な機能を理解するための実践的な例を含んでいます。

## 📚 プロジェクト概要

このプロジェクトは、JSFの以下の概念を学習するためのサンプルです：

- **Managed Bean**: `@Named`と`@RequestScoped`の使い方
- **JSFライフサイクル**: 6つのフェーズの動作確認
- **バリデーション**: フォーム入力値の検証
- **EL式**: Expression Languageの使用方法
- **XHTML**: JSFのビューテンプレート

## 🛠️ 技術スタック

- **Java**: Jakarta EE
- **JSF**: Jakarta Faces 4.0.2
- **CDI**: Jakarta Enterprise CDI 4.0.1
- **Weld**: CDI実装 (5.1.2.Final)
- **Maven**: ビルドツール

## 📁 プロジェクト構成

```
jsf-sample/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/example/
│       │       ├── HelloBean.java          # シンプルなManaged Bean
│       │       ├── UserBean.java           # ユーザー入力用Bean
│       │       ├── LifecycleBean.java      # ライフサイクル学習用Bean
│       │       └── UserFormDTO.java        # データ転送オブジェクト
│       └── webapp/
│           ├── index.xhtml                  # トップページ
│           ├── lifecycle.xhtml             # ライフサイクル学習ページ
│           └── WEB-INF/
│               ├── web.xml                  # Webアプリケーション設定
│               └── beans.xml                # CDI設定
├── LEARNING_GUIDE.md                        # 学習ガイド
├── LEARNING_GUIDE_ANSWERS.md                # 理解度チェックリスト解答
└── pom.xml                                  # Maven設定
```

## 🚀 セットアップ

### 前提条件

- Java 11以上
- Maven 3.6以上
- Tomcat 10以上（または互換性のあるアプリケーションサーバー）

### ビルド

```bash
mvn clean package
```

### 実行

生成された`target/jsf-sample.war`をTomcatなどのアプリケーションサーバーにデプロイしてください。

または、MavenのTomcatプラグインを使用する場合：

```bash
mvn tomcat7:run
```

## 📖 学習リソース

### 学習ガイド

- **`LEARNING_GUIDE.md`**: 段階的な練習課題と学習の進め方
- **`LEARNING_GUIDE_ANSWERS.md`**: 理解度チェックリストの解答

### ページ構成

1. **index.xhtml**: 基本的なManaged Beanの動作確認
2. **lifecycle.xhtml**: JSFライフサイクルの6つのフェーズを視覚的に確認

## 🎯 主な機能

### 1. 基本的なManaged Bean

`HelloBean`と`UserBean`で、シンプルなデータバインディングとアクションメソッドの動作を確認できます。

### 2. JSFライフサイクルの学習

`LifecycleBean`を使用して、以下のフェーズを学習できます：

1. **Restore View** - ビューの復元
2. **Apply Request Values** - リクエスト値の適用
3. **Process Validations** - 検証
4. **Update Model Values** - モデル更新
5. **Invoke Application** - アプリケーション呼び出し
6. **Render Response** - レスポンスレンダリング

### 3. バリデーション

- 必須チェック（`required="true"`）
- カスタムバリデーション（年齢、メールアドレス）
- エラーメッセージの表示

## 📝 開発

### コードの追加

新しい機能を追加する際は、`LEARNING_GUIDE.md`の練習課題を参考にしてください。

### Git管理

このプロジェクトをGitで管理する場合は、`GIT_SETUP.md`を参照してください。

## 🔗 参考リンク

- [Jakarta Faces公式サイト](https://jakarta.ee/specifications/faces/)
- [Jakarta CDI公式サイト](https://jakarta.ee/specifications/cdi/)
- [Weld公式サイト](https://weld.cdi-spec.org/)

## 📄 ライセンス

このプロジェクトは学習目的のサンプルコードです。

## 🤝 貢献

学習目的のプロジェクトですが、改善提案やバグ報告は歓迎します。

---

**注意**: このプロジェクトは学習用のサンプルです。本番環境で使用する場合は、セキュリティやパフォーマンスの考慮が必要です。

