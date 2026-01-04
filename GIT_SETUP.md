# Gitセットアップ手順

このプロジェクトをGitリポジトリとして管理し、GitHubなどにpushするための手順です。

## 📋 前提条件

- Gitがインストールされていること
- GitHubアカウント（または他のGitホスティングサービス）を持っていること

## 🚀 セットアップ手順

### 1. Gitリポジトリの初期化

プロジェクトのルートディレクトリで以下を実行：

```bash
cd /Users/takuyamatsuo/jsf-sample
git init
```

### 2. ファイルをステージングに追加

`.gitignore`ファイルが既に作成されているので、必要なファイルを追加します：

```bash
git add .
```

このコマンドで、`.gitignore`で除外されていないファイルがすべて追加されます。
（`target/`ディレクトリや`.class`ファイルは自動的に除外されます）

### 3. 初回コミット

```bash
git commit -m "Initial commit: JSF sample project with lifecycle learning"
```

### 4. リモートリポジトリの準備

#### GitHubの場合

1. GitHubにログイン
2. 右上の「+」ボタンから「New repository」を選択
3. リポジトリ名を入力（例: `jsf-sample`）
4. 「Create repository」をクリック
5. 表示されるURLをコピー（例: `https://github.com/your-username/jsf-sample.git`）

### 5. リモートリポジトリの追加

```bash
git remote add origin https://github.com/your-username/jsf-sample.git
```

**注意**: `your-username`と`jsf-sample`を実際の値に置き換えてください。

### 6. ブランチ名の設定（オプション）

GitHubのデフォルトブランチ名が`main`の場合：

```bash
git branch -M main
```

### 7. プッシュ

```bash
git push -u origin main
```

または、ブランチ名が`master`の場合：

```bash
git push -u origin master
```

## 📝 今後の作業フロー

### 変更をコミットしてプッシュする場合

```bash
# 変更されたファイルを確認
git status

# 変更をステージングに追加
git add .

# または、特定のファイルだけ追加
git add src/main/java/com/example/LifecycleBean.java

# コミット
git commit -m "説明: 何を変更したか"

# プッシュ
git push
```

### 新しいファイルを追加した場合

```bash
git add 新しいファイル名
git commit -m "説明: 何を追加したか"
git push
```

## 🔍 .gitignoreについて

`.gitignore`ファイルには、以下のファイル/ディレクトリが含まれています：

- `target/` - Mavenのビルド成果物
- `*.class` - コンパイル済みクラスファイル
- `*.jar`, `*.war` - パッケージファイル
- `.idea/`, `.vscode/` - IDE設定ファイル
- `.DS_Store` - macOSのシステムファイル

これらはGitで管理する必要がないファイルです。

## ⚠️ トラブルシューティング

### 認証エラーが出る場合

GitHubでは、パスワード認証が廃止されています。以下のいずれかの方法を使用してください：

#### 方法1: Personal Access Token (PAT) を使用

1. GitHub → Settings → Developer settings → Personal access tokens → Tokens (classic)
2. 「Generate new token」をクリック
3. 必要な権限を選択（`repo`権限が必要）
4. トークンをコピー
5. プッシュ時にパスワードの代わりにトークンを入力

#### 方法2: SSH認証を使用

```bash
# SSH鍵を生成（まだ持っていない場合）
ssh-keygen -t ed25519 -C "your_email@example.com"

# SSH鍵をGitHubに登録
# 1. ~/.ssh/id_ed25519.pub の内容をコピー
# 2. GitHub → Settings → SSH and GPG keys → New SSH key
# 3. 鍵を貼り付けて保存

# リモートURLをSSHに変更
git remote set-url origin git@github.com:your-username/jsf-sample.git
```

### 既存のリモートリポジトリがある場合

```bash
# 現在のリモートを確認
git remote -v

# リモートを削除
git remote remove origin

# 新しいリモートを追加
git remote add origin https://github.com/your-username/jsf-sample.git
```

## 📚 参考リンク

- [Git公式ドキュメント](https://git-scm.com/doc)
- [GitHub Docs](https://docs.github.com/)
- [GitHub CLI](https://cli.github.com/) - コマンドラインからGitHubを操作

## ✅ 確認事項

プッシュが成功したら、GitHubのリポジトリページで以下を確認してください：

- ✅ すべてのソースファイルが表示されている
- ✅ `target/`ディレクトリが表示されていない（.gitignoreが機能している）
- ✅ READMEファイルがあれば表示されている

---

**注意**: 機密情報（パスワード、APIキーなど）が含まれるファイルは、絶対にコミットしないでください。
もし誤ってコミットしてしまった場合は、`git filter-branch`や`git filter-repo`を使用して履歴から削除する必要があります。

