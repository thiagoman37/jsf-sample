# Java学習ガイド - 理解度チェックリスト 解答編

このドキュメントは、`JAVA_LEARNING_GUIDE.md`の「理解度チェックリスト」に対する詳細な解答です。
各質問に具体的なコード例と説明を提供しています。

---

## 基礎編

### 1. クラスとオブジェクトの違い

#### クラスとは何か？オブジェクトとは何か？

**クラス**は、オブジェクトの設計図やテンプレートです。データ（フィールド）と動作（メソッド）を定義します。

**オブジェクト**は、クラスから作成された実体（インスタンス）です。実際のデータを持ち、メソッドを実行できます。

**具体例**:
```java
// クラスの定義（設計図）
public class Person {
    private String name;
    private int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public void introduce() {
        System.out.println("こんにちは、" + name + "です。年齢は" + age + "歳です。");
    }
}

// オブジェクトの作成（実体）
Person person1 = new Person("田中太郎", 25);  // person1はオブジェクト
Person person2 = new Person("佐藤花子", 30);  // person2は別のオブジェクト
```

**比喩で理解する**:
- クラス = 自動車の設計図
- オブジェクト = 設計図から作られた実際の自動車（1台目、2台目...）

#### newキーワードは何をしているか？

`new`キーワードは、**メモリ上にオブジェクトを新しく作成する**ことを指示します。

**動作の流れ**:
```java
Person person = new Person("田中太郎", 25);
```

1. `new Person(...)` - メモリ上に`Person`オブジェクトの領域を確保
2. コンストラクタ`Person(String name, int age)`が呼ばれる
3. フィールド`name`と`age`が初期化される
4. 作成されたオブジェクトの参照（メモリアドレス）が`person`変数に代入される

**メモリの状態**:
```
メモリ上:
[Personオブジェクト1]
  name = "田中太郎"
  age = 25
  ↑
  person変数がこのオブジェクトを参照
```

#### インスタンス化とは何か？

**インスタンス化**は、クラスからオブジェクト（インスタンス）を作成する過程です。

```java
// インスタンス化の例
Person person1 = new Person("田中太郎", 25);  // インスタンス1を作成
Person person2 = new Person("佐藤花子", 30);  // インスタンス2を作成
Person person3 = new Person("鈴木一郎", 20);  // インスタンス3を作成
```

**重要なポイント**:
- 1つのクラスから、複数のインスタンスを作成できる
- 各インスタンスは独立したデータを持つ
- `person1`の`name`を変更しても、`person2`の`name`には影響しない

---

### 2. メソッドの役割

#### メソッドと関数の違いは？

Javaでは、**メソッド**はクラス内に定義された関数です。他の言語（C言語など）では、クラス外でも関数を定義できますが、Javaでは基本的にすべてメソッドです。

**メソッドの定義**:
```java
public class Calculator {
    // メソッドの定義
    public int add(int a, int b) {
        return a + b;
    }
}
```

**関数（他の言語）との違い**:
- C言語: クラス外でも関数を定義できる
- Java: すべての関数はクラス内のメソッドとして定義される

#### voidの意味は？

`void`は、「戻り値がない」ことを示すキーワードです。

**戻り値がある場合**:
```java
public int add(int a, int b) {
    return a + b;  // int型の値を返す
}
```

**戻り値がない場合（void）**:
```java
public void printMessage(String message) {
    System.out.println(message);  // 何も返さない
}
```

**使用例**:
```java
int result = calculator.add(5, 3);  // 戻り値を受け取る
calculator.printMessage("こんにちは");  // 戻り値はない
```

#### 戻り値と引数の違いは？

**引数（パラメータ）**: メソッドに**渡す**値
**戻り値（返り値）**: メソッドから**返ってくる**値

**具体例**:
```java
public int multiply(int a, int b) {
    // a と b が引数（メソッドに渡される値）
    return a * b;  // 戻り値（メソッドから返される値）
}

// 使用例
int result = multiply(5, 3);
// 引数: 5 と 3 を渡す
// 戻り値: 15 が返ってくる
```

**図解**:
```
メソッド呼び出し: multiply(5, 3)
         ↓
    [引数を受け取る]
    a = 5, b = 3
         ↓
    [処理を実行]
    return 5 * 3
         ↓
    [戻り値を返す]
    result = 15
```

---

### 3. 変数の種類

#### ローカル変数、インスタンス変数、クラス変数の違いは？

**1. ローカル変数**
- メソッド内で宣言される変数
- そのメソッド内でのみ有効
- 初期化しないと使えない

```java
public void method() {
    int localVar = 10;  // ローカル変数
    System.out.println(localVar);
    // メソッドを抜けると、localVarは破棄される
}
```

**2. インスタンス変数**
- クラス内で宣言される変数（`static`なし）
- 各インスタンスごとに独立した値を持つ
- デフォルト値が設定される（intは0、Stringはnullなど）

```java
public class Person {
    private String name;  // インスタンス変数
    private int age;      // インスタンス変数
    
    // person1.name と person2.name は別々の値
}
```

**3. クラス変数（static変数）**
- `static`キーワードで宣言される変数
- すべてのインスタンスで共有される
- クラス名で直接アクセスできる

```java
public class Person {
    private static int count = 0;  // クラス変数
    
    public Person() {
        count++;  // すべてのインスタンスで共有される
    }
    
    public static int getCount() {
        return count;  // Person.getCount() でアクセス
    }
}
```

**比較表**:

| 種類 | 宣言場所 | スコープ | 共有 | 初期化 |
|------|---------|---------|------|--------|
| ローカル変数 | メソッド内 | メソッド内のみ | - | 必須 |
| インスタンス変数 | クラス内 | インスタンス全体 | 各インスタンスで独立 | 自動（デフォルト値） |
| クラス変数 | クラス内（static） | クラス全体 | 全インスタンスで共有 | 自動（デフォルト値） |

#### staticキーワードの意味は？

`static`は、「クラスに属する」ことを示すキーワードです。インスタンスを作成しなくても使用できます。

**staticメソッドの例**:
```java
public class MathUtils {
    public static int add(int a, int b) {
        return a + b;
    }
}

// インスタンスを作らずに呼び出せる
int result = MathUtils.add(5, 3);
```

**static変数の例**:
```java
public class Counter {
    private static int count = 0;  // クラス変数
    
    public Counter() {
        count++;
    }
    
    public static int getCount() {
        return count;  // すべてのインスタンスで共有
    }
}

Counter c1 = new Counter();
Counter c2 = new Counter();
System.out.println(Counter.getCount());  // 2（2つのインスタンスが作成された）
```

**staticの特徴**:
- インスタンスを作成しなくても使用できる
- すべてのインスタンスで共有される
- `this`キーワードが使えない（インスタンスに属していないため）

#### finalキーワードの意味は？

`final`は、「変更できない」ことを示すキーワードです。用途によって意味が異なります。

**1. final変数（定数）**:
```java
public class Constants {
    public static final double PI = 3.14159;  // 定数（変更不可）
    public static final int MAX_SIZE = 100;
}

// 使用例
double area = Constants.PI * radius * radius;
```

**2. finalメソッド（オーバーライド不可）**:
```java
public class Parent {
    public final void method() {  // 子クラスでオーバーライドできない
        System.out.println("親クラスのメソッド");
    }
}
```

**3. finalクラス（継承不可）**:
```java
public final class String {  // 継承できない
    // ...
}
```

**使用例**:
```java
public class Person {
    private final String name;  // 一度設定したら変更不可
    
    public Person(String name) {
        this.name = name;  // コンストラクタで初期化
    }
    
    // nameを変更するメソッドは作れない
}
```

---

### 4. アクセス修飾子

#### public、private、protected、パッケージプライベートの違いは？

アクセス修飾子は、クラス、メソッド、フィールドへのアクセス範囲を制御します。

**アクセス範囲の比較**:

| 修飾子 | 同じクラス | 同じパッケージ | サブクラス | その他 |
|--------|-----------|--------------|-----------|--------|
| `private` | ✓ | ✗ | ✗ | ✗ |
| （なし）パッケージプライベート | ✓ | ✓ | ✗ | ✗ |
| `protected` | ✓ | ✓ | ✓ | ✗ |
| `public` | ✓ | ✓ | ✓ | ✓ |

**具体例**:
```java
package com.example;

public class Person {
    private String name;           // 同じクラス内のみ
    int age;                       // パッケージプライベート（同じパッケージ内）
    protected String email;        // 同じパッケージ + サブクラス
    public String address;         // どこからでもアクセス可能
    
    public void setName(String name) {
        this.name = name;  // privateでも同じクラス内ならアクセス可能
    }
}
```

**別パッケージのクラスから**:
```java
package com.other;

import com.example.Person;

public class Test {
    public void test() {
        Person person = new Person();
        // person.name;      // エラー: private
        // person.age;       // エラー: パッケージプライベート
        // person.email;     // エラー: protected（サブクラスでないため）
        person.address;     // OK: public
    }
}
```

#### なぜprivateを使うのか？

`private`を使う理由は、**カプセル化**（データの隠蔽）のためです。

**問題のあるコード（publicフィールド）**:
```java
public class Person {
    public int age;  // 直接アクセス可能
}

// 使用例
Person person = new Person();
person.age = -10;  // 無効な値が設定できてしまう！
```

**改善されたコード（private + Getter/Setter）**:
```java
public class Person {
    private int age;  // 直接アクセス不可
    
    public void setAge(int age) {
        if (age < 0 || age > 150) {
            throw new IllegalArgumentException("年齢は0から150の間で入力してください");
        }
        this.age = age;  // バリデーションを追加
    }
    
    public int getAge() {
        return age;
    }
}

// 使用例
Person person = new Person();
person.setAge(-10);  // 例外が投げられる（無効な値が防げる）
```

**privateの利点**:
1. **データの整合性を保つ**: 無効な値の設定を防げる
2. **実装の変更が容易**: 内部実装を変更しても、外部コードに影響しない
3. **デバッグが容易**: 値の変更箇所を制限できる

#### Getter/Setterパターンはなぜ必要か？

Getter/Setterパターンは、`private`フィールドに安全にアクセスするための標準的な方法です。

**Getter/Setterの例**:
```java
public class Person {
    private String name;
    private int age;
    
    // Getter: 値を取得
    public String getName() {
        return name;
    }
    
    // Setter: 値を設定（バリデーション付き）
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("名前は必須です");
        }
        this.name = name;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        if (age < 0 || age > 150) {
            throw new IllegalArgumentException("年齢は0から150の間で入力してください");
        }
        this.age = age;
    }
}
```

**Getter/Setterの利点**:
1. **バリデーション**: 値を設定する際に検証できる
2. **ロギング**: 値の変更を記録できる
3. **計算プロパティ**: 実際のフィールドがなくても、計算して返せる
4. **後方互換性**: 実装を変更しても、外部インターフェースは維持できる

**計算プロパティの例**:
```java
public class Rectangle {
    private int width;
    private int height;
    
    // 実際のフィールドはないが、Getterで計算して返す
    public int getArea() {
        return width * height;
    }
}
```

---

### 5. コンストラクタの役割

#### コンストラクタはいつ呼ばれるか？

コンストラクタは、**オブジェクトが作成される時（`new`キーワードが使われる時）**に自動的に呼ばれます。

**例**:
```java
public class Person {
    private String name;
    private int age;
    
    // コンストラクタ
    public Person(String name, int age) {
        System.out.println("コンストラクタが呼ばれました");
        this.name = name;
        this.age = age;
    }
}

// 使用例
Person person = new Person("田中太郎", 25);
// この時点でコンストラクタが自動的に呼ばれる
// 出力: "コンストラクタが呼ばれました"
```

**呼び出しのタイミング**:
1. `new Person(...)`が実行される
2. メモリ上にオブジェクトの領域が確保される
3. コンストラクタが自動的に呼ばれる
4. コンストラクタ内の処理が実行される
5. オブジェクトの参照が返される

#### デフォルトコンストラクタとは何か？

**デフォルトコンストラクタ**は、引数を持たないコンストラクタで、明示的に定義しなくても自動的に提供されます。

**自動的に提供される場合**:
```java
public class Person {
    private String name;
    // コンストラクタを定義していない
}

// 使用可能（デフォルトコンストラクタが自動的に提供される）
Person person = new Person();
```

**自動的に提供されない場合**:
```java
public class Person {
    private String name;
    
    // コンストラクタを1つでも定義すると、デフォルトコンストラクタは提供されない
    public Person(String name) {
        this.name = name;
    }
}

// エラー: デフォルトコンストラクタは存在しない
// Person person = new Person();  // コンパイルエラー

// OK: 定義したコンストラクタを使う
Person person = new Person("田中太郎");
```

**デフォルトコンストラクタを明示的に定義する場合**:
```java
public class Person {
    private String name;
    
    // デフォルトコンストラクタを明示的に定義
    public Person() {
        this.name = "名無し";
    }
    
    public Person(String name) {
        this.name = name;
    }
}

// どちらも使用可能
Person person1 = new Person();           // デフォルトコンストラクタ
Person person2 = new Person("田中太郎");  // 引数ありのコンストラクタ
```

#### コンストラクタのオーバーロードはなぜ必要か？

**コンストラクタのオーバーロード**は、異なる初期化方法を提供するために必要です。

**例**:
```java
public class Person {
    private String name;
    private int age;
    private String email;
    
    // コンストラクタ1: 名前と年齢のみ
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        this.email = null;
    }
    
    // コンストラクタ2: 名前、年齢、メールアドレス
    public Person(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }
    
    // コンストラクタ3: 名前のみ（年齢は0）
    public Person(String name) {
        this.name = name;
        this.age = 0;
        this.email = null;
    }
}

// 使用例
Person person1 = new Person("田中太郎", 25);
Person person2 = new Person("佐藤花子", 30, "sato@example.com");
Person person3 = new Person("鈴木一郎");
```

**オーバーロードの利点**:
1. **柔軟性**: 様々な初期化方法を提供できる
2. **利便性**: 必要な情報だけを渡してオブジェクトを作成できる
3. **デフォルト値**: 一部のフィールドにデフォルト値を設定できる

**this()を使った実装**:
```java
public class Person {
    private String name;
    private int age;
    private String email;
    
    // 完全なコンストラクタ
    public Person(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }
    
    // 他のコンストラクタから完全なコンストラクタを呼び出す
    public Person(String name, int age) {
        this(name, age, null);  // this()で他のコンストラクタを呼び出し
    }
    
    public Person(String name) {
        this(name, 0, null);
    }
}
```

---

## 応用編

### 6. 継承とポリモーフィズム

#### extendsキーワードの意味は？

`extends`は、クラスを継承する際に使用するキーワードです。親クラス（スーパークラス）の機能を子クラス（サブクラス）が引き継ぎます。

**基本構文**:
```java
class 子クラス extends 親クラス {
    // 子クラスのコード
}
```

**具体例**:
```java
// 親クラス
public class Animal {
    protected String name;
    protected int age;
    
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public void makeSound() {
        System.out.println("音を出す");
    }
    
    public void introduce() {
        System.out.println("私は" + name + "です。年齢は" + age + "歳です。");
    }
}

// 子クラス
public class Dog extends Animal {
    public Dog(String name, int age) {
        super(name, age);  // 親クラスのコンストラクタを呼び出す
    }
    
    // 親クラスのメソッドをオーバーライド
    @Override
    public void makeSound() {
        System.out.println("ワンワン");
    }
}

// 使用例
Dog dog = new Dog("ポチ", 3);
dog.introduce();    // 親クラスのメソッドを使用
dog.makeSound();    // 子クラスでオーバーライドしたメソッドを使用
```

**継承の利点**:
1. **コードの再利用**: 親クラスのコードを再利用できる
2. **一貫性**: 共通の機能を親クラスに集約できる
3. **拡張性**: 子クラスで機能を追加・変更できる

#### オーバーライドとオーバーロードの違いは？

**オーバーライド（Override）**: 親クラスのメソッドを子クラスで**再定義**すること

**オーバーロード（Overload）**: 同じクラス内で、同じ名前だが**引数が異なる**メソッドを複数定義すること

**オーバーライドの例**:
```java
public class Animal {
    public void makeSound() {
        System.out.println("音を出す");
    }
}

public class Dog extends Animal {
    @Override  // アノテーション（推奨）
    public void makeSound() {  // 親クラスのメソッドを上書き
        System.out.println("ワンワン");
    }
}

// 使用例
Animal animal = new Dog();
animal.makeSound();  // "ワンワン" が出力される（子クラスの実装が使われる）
```

**オーバーロードの例**:
```java
public class Calculator {
    // 同じメソッド名だが、引数が異なる
    public int add(int a, int b) {
        return a + b;
    }
    
    public int add(int a, int b, int c) {  // 引数の数が異なる
        return a + b + c;
    }
    
    public double add(double a, double b) {  // 引数の型が異なる
        return a + b;
    }
}

// 使用例
Calculator calc = new Calculator();
calc.add(5, 3);        // int版が呼ばれる
calc.add(5, 3, 2);     // 3引数版が呼ばれる
calc.add(5.5, 3.2);    // double版が呼ばれる
```

**比較表**:

| 項目 | オーバーライド | オーバーロード |
|------|--------------|--------------|
| 関係 | 親クラスと子クラス | 同じクラス内 |
| メソッド名 | 同じ | 同じ |
| 引数 | 同じ | 異なる |
| 戻り値の型 | 同じかサブタイプ | 異なってもよい |
| アクセス修飾子 | 同じかより広い | 異なってもよい |
| `@Override` | 推奨 | 不要 |

#### superキーワードは何を参照するか？

`super`は、**親クラス（スーパークラス）**を参照するキーワードです。

**主な用途**:

1. **親クラスのコンストラクタを呼び出す**:
```java
public class Animal {
    protected String name;
    
    public Animal(String name) {
        this.name = name;
    }
}

public class Dog extends Animal {
    private String breed;
    
    public Dog(String name, String breed) {
        super(name);  // 親クラスのコンストラクタを呼び出す
        this.breed = breed;
    }
}
```

2. **親クラスのメソッドを呼び出す**:
```java
public class Animal {
    public void makeSound() {
        System.out.println("音を出す");
    }
}

public class Dog extends Animal {
    @Override
    public void makeSound() {
        super.makeSound();  // 親クラスのメソッドを呼び出す
        System.out.println("ワンワン");
    }
}

// 使用例
Dog dog = new Dog("ポチ", 3);
dog.makeSound();
// 出力:
// 音を出す
// ワンワン
```

3. **親クラスのフィールドにアクセス**:
```java
public class Animal {
    protected String name;  // protectedなので子クラスからアクセス可能
}

public class Dog extends Animal {
    public void printName() {
        System.out.println(super.name);  // 親クラスのフィールドにアクセス
        // または単に name でもOK（this.name と同じ）
    }
}
```

**thisとsuperの違い**:
- `this`: 現在のクラス（自分自身）を参照
- `super`: 親クラスを参照

---

### 7. インターフェースと抽象クラス

#### インターフェースと抽象クラスの違いは？

**インターフェース**:
- メソッドの**宣言のみ**（実装はない）
- 複数のインターフェースを実装できる（多重継承的な機能）
- フィールドは`public static final`のみ（定数のみ）

**抽象クラス**:
- メソッドの**宣言と実装の両方**を持てる
- 1つのクラスしか継承できない
- 通常のフィールドを持てる

**インターフェースの例**:
```java
// インターフェースの定義
public interface Drawable {
    void draw();  // 実装はない（宣言のみ）
    
    // Java 8以降は default メソッドで実装も持てる
    default void printInfo() {
        System.out.println("描画可能なオブジェクトです");
    }
}

// インターフェースの実装
public class Circle implements Drawable {
    @Override
    public void draw() {
        System.out.println("円を描画します");
    }
}

public class Rectangle implements Drawable {
    @Override
    public void draw() {
        System.out.println("四角形を描画します");
    }
}
```

**抽象クラスの例**:
```java
// 抽象クラスの定義
public abstract class Animal {
    protected String name;  // 通常のフィールド
    
    public Animal(String name) {
        this.name = name;
    }
    
    // 抽象メソッド（実装がない）
    public abstract void makeSound();
    
    // 通常のメソッド（実装がある）
    public void introduce() {
        System.out.println("私は" + name + "です");
    }
}

// 抽象クラスの継承
public class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }
    
    @Override
    public void makeSound() {
        System.out.println("ワンワン");
    }
}
```

**比較表**:

| 項目 | インターフェース | 抽象クラス |
|------|----------------|-----------|
| メソッドの実装 | 基本的にない（Java 8以降はdefaultメソッド可） | 持てる |
| フィールド | `public static final`のみ | 通常のフィールド可 |
| 継承 | 複数実装可能 | 1つだけ継承可能 |
| コンストラクタ | 持てない | 持てる |
| 使用目的 | 契約（契約）の定義 | 共通実装の提供 |

#### いつインターフェースを使い、いつ抽象クラスを使うか？

**インターフェースを使う場合**:
- 複数のクラスで共通の**契約**を定義したい
- 実装の詳細が異なるが、**同じメソッド名**を使いたい
- 複数のインターフェースを実装したい

**例**:
```java
// インターフェース: 契約の定義
public interface Flyable {
    void fly();
}

public interface Swimmable {
    void swim();
}

// 複数のインターフェースを実装
public class Duck implements Flyable, Swimmable {
    @Override
    public void fly() {
        System.out.println("飛びます");
    }
    
    @Override
    public void swim() {
        System.out.println("泳ぎます");
    }
}
```

**抽象クラスを使う場合**:
- 複数のクラスで**共通の実装**を共有したい
- 共通のフィールドやメソッドを持ちたい
- テンプレートメソッドパターンを実装したい

**例**:
```java
// 抽象クラス: 共通実装の提供
public abstract class Vehicle {
    protected String brand;  // 共通フィールド
    
    public Vehicle(String brand) {
        this.brand = brand;
    }
    
    // 共通メソッド
    public void start() {
        System.out.println(brand + "がエンジンを始動しました");
    }
    
    // 抽象メソッド（子クラスで実装）
    public abstract void move();
}

public class Car extends Vehicle {
    public Car(String brand) {
        super(brand);
    }
    
    @Override
    public void move() {
        System.out.println("道路を走ります");
    }
}
```

#### implementsとextendsの違いは？

**`extends`**: クラスを継承する際に使用
**`implements`**: インターフェースを実装する際に使用

**extendsの例**:
```java
public class Animal {
    // 親クラス
}

public class Dog extends Animal {
    // クラスを継承
}
```

**implementsの例**:
```java
public interface Drawable {
    void draw();
}

public class Circle implements Drawable {
    // インターフェースを実装
    @Override
    public void draw() {
        System.out.println("円を描画");
    }
}
```

**両方を使う例**:
```java
public class Animal {
    // 親クラス
}

public interface Flyable {
    void fly();
}

public class Bird extends Animal implements Flyable {
    // クラスを継承し、インターフェースを実装
    @Override
    public void fly() {
        System.out.println("飛びます");
    }
}
```

**重要なポイント**:
- クラスは1つしか継承できない（`extends`は1つだけ）
- インターフェースは複数実装できる（`implements`は複数可能）
- `extends`と`implements`を同時に使える

---

### 8. 例外処理

#### try-catch-finallyの役割は？

**`try`**: 例外が発生する可能性があるコードを記述
**`catch`**: 例外が発生した時に実行される処理
**`finally`**: 例外の有無に関わらず、必ず実行される処理

**基本構文**:
```java
try {
    // 例外が発生する可能性があるコード
} catch (例外の型 変数名) {
    // 例外が発生した時の処理
} finally {
    // 必ず実行される処理
}
```

**具体例**:
```java
public class Example {
    public static void main(String[] args) {
        try {
            int result = divide(10, 0);  // 例外が発生する可能性
            System.out.println("結果: " + result);
        } catch (ArithmeticException e) {
            System.out.println("エラー: " + e.getMessage());  // 例外処理
        } finally {
            System.out.println("処理が完了しました");  // 必ず実行される
        }
    }
    
    public static int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("0で割ることはできません");
        }
        return a / b;
    }
}

// 出力:
// エラー: 0で割ることはできません
// 処理が完了しました
```

**finallyの役割**:
- リソースの解放（ファイルのクローズなど）
- クリーンアップ処理
- ログの出力

**例: ファイル処理**:
```java
FileReader reader = null;
try {
    reader = new FileReader("file.txt");
    // ファイルを読む処理
} catch (IOException e) {
    System.out.println("ファイル読み込みエラー: " + e.getMessage());
} finally {
    if (reader != null) {
        try {
            reader.close();  // 必ずファイルを閉じる
        } catch (IOException e) {
            // クローズ時のエラー処理
        }
    }
}
```

**try-with-resources（Java 7以降）**:
```java
// 自動的にリソースを閉じてくれる
try (FileReader reader = new FileReader("file.txt")) {
    // ファイルを読む処理
} catch (IOException e) {
    System.out.println("エラー: " + e.getMessage());
}  // finallyが不要（自動的にcloseされる）
```

#### チェック例外と非チェック例外の違いは？

**チェック例外（Checked Exception）**:
- コンパイル時にチェックされる
- `try-catch`で処理するか、`throws`で宣言する必要がある
- `Exception`のサブクラス（`RuntimeException`を除く）

**非チェック例外（Unchecked Exception）**:
- コンパイル時にチェックされない
- 処理しなくてもコンパイルエラーにならない
- `RuntimeException`とそのサブクラス、`Error`とそのサブクラス

**チェック例外の例**:
```java
import java.io.FileReader;
import java.io.IOException;

public class Example {
    // チェック例外をthrowsで宣言
    public static void readFile() throws IOException {
        FileReader reader = new FileReader("file.txt");
        reader.close();
    }
    
    // またはtry-catchで処理
    public static void readFile2() {
        try {
            FileReader reader = new FileReader("file.txt");
            reader.close();
        } catch (IOException e) {  // チェック例外をキャッチ
            System.out.println("エラー: " + e.getMessage());
        }
    }
}
```

**非チェック例外の例**:
```java
public class Example {
    public static void divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("0で割ることはできません");
            // RuntimeExceptionのサブクラスなので、throws宣言不要
        }
        System.out.println(a / b);
    }
    
    public static void main(String[] args) {
        divide(10, 0);  // コンパイルエラーにならない
        // 実行時に例外が発生する
    }
}
```

**例外の階層**:
```
Throwable
├── Error (非チェック例外)
│   ├── OutOfMemoryError
│   └── StackOverflowError
└── Exception
    ├── RuntimeException (非チェック例外)
    │   ├── NullPointerException
    │   ├── ArrayIndexOutOfBoundsException
    │   └── ArithmeticException
    └── その他のException (チェック例外)
        ├── IOException
        ├── FileNotFoundException
        └── SQLException
```

**比較表**:

| 項目 | チェック例外 | 非チェック例外 |
|------|------------|--------------|
| コンパイル時チェック | あり | なし |
| 処理の必要性 | 必須 | 任意 |
| 例 | `IOException`, `SQLException` | `NullPointerException`, `ArithmeticException` |
| 使用場面 | 回復可能なエラー | プログラミングエラー |

#### throwsとthrowの違いは？

**`throws`**: メソッドの宣言で、そのメソッドが例外を**投げる可能性がある**ことを示す
**`throw`**: 実際に例外を**投げる**（発生させる）

**throwsの例**:
```java
// メソッドがIOExceptionを投げる可能性があることを宣言
public void readFile() throws IOException {
    FileReader reader = new FileReader("file.txt");
    // ...
}
```

**throwの例**:
```java
public int divide(int a, int b) {
    if (b == 0) {
        throw new ArithmeticException("0で割ることはできません");
        // 実際に例外を投げる
    }
    return a / b;
}
```

**組み合わせた例**:
```java
public class Calculator {
    // カスタム例外を定義
    public static class DivisionByZeroException extends Exception {
        public DivisionByZeroException(String message) {
            super(message);
        }
    }
    
    // throwsで例外を投げる可能性を宣言
    public static int divide(int a, int b) throws DivisionByZeroException {
        if (b == 0) {
            // throwで実際に例外を投げる
            throw new DivisionByZeroException("0で割ることはできません");
        }
        return a / b;
    }
    
    // 使用例
    public static void main(String[] args) {
        try {
            int result = divide(10, 0);
        } catch (DivisionByZeroException e) {
            System.out.println("エラー: " + e.getMessage());
        }
    }
}
```

**重要なポイント**:
- `throws`はメソッドの**宣言**に書く
- `throw`はメソッドの**本体**に書く
- チェック例外を投げる場合は、`throws`で宣言する必要がある
- 非チェック例外の場合は、`throws`宣言は任意

---

### 9. コレクションフレームワーク

#### List、Set、Mapの違いは？

**List**: 順序があり、重複を許すコレクション
**Set**: 順序がなく（または順序付き）、重複を許さないコレクション
**Map**: キーと値のペアを格納するコレクション

**Listの例**:
```java
import java.util.ArrayList;
import java.util.List;

List<String> list = new ArrayList<>();
list.add("りんご");
list.add("バナナ");
list.add("りんご");  // 重複可能
list.add("オレンジ");

System.out.println(list);  // [りんご, バナナ, りんご, オレンジ]
System.out.println(list.get(0));  // りんご（インデックスでアクセス）
```

**Setの例**:
```java
import java.util.HashSet;
import java.util.Set;

Set<String> set = new HashSet<>();
set.add("りんご");
set.add("バナナ");
set.add("りんご");  // 重複は無視される
set.add("オレンジ");

System.out.println(set);  // [りんご, バナナ, オレンジ]（順序は保証されない）
```

**Mapの例**:
```java
import java.util.HashMap;
import java.util.Map;

Map<String, Integer> map = new HashMap<>();
map.put("りんご", 100);
map.put("バナナ", 80);
map.put("オレンジ", 120);

System.out.println(map.get("りんご"));  // 100（キーで値を取得）
System.out.println(map);  // {りんご=100, バナナ=80, オレンジ=120}
```

**比較表**:

| 種類 | 順序 | 重複 | アクセス方法 | 実装クラス例 |
|------|------|------|------------|------------|
| List | あり | 可 | インデックス | `ArrayList`, `LinkedList` |
| Set | なし/あり | 不可 | 要素そのもの | `HashSet`, `TreeSet` |
| Map | なし/あり | キーは不可 | キー | `HashMap`, `TreeMap` |

#### ArrayListとLinkedListの違いは？

**ArrayList**: 内部で配列を使用。ランダムアクセスが高速
**LinkedList**: 内部でリンクリストを使用。挿入・削除が高速

**ArrayListの特徴**:
```java
import java.util.ArrayList;
import java.util.List;

List<String> list = new ArrayList<>();
list.add("A");
list.add("B");
list.add("C");

// ランダムアクセスが高速（O(1)）
String element = list.get(1);  // "B"を取得（高速）

// 挿入・削除が遅い（O(n)）
list.add(1, "X");  // 位置1に挿入（要素をシフトする必要がある）
```

**LinkedListの特徴**:
```java
import java.util.LinkedList;
import java.util.List;

List<String> list = new LinkedList<>();
list.add("A");
list.add("B");
list.add("C");

// ランダムアクセスが遅い（O(n)）
String element = list.get(1);  // "B"を取得（先頭から順に探す）

// 挿入・削除が高速（O(1)）
list.add(1, "X");  // 位置1に挿入（リンクを変更するだけ）
```

**比較表**:

| 操作 | ArrayList | LinkedList |
|------|-----------|------------|
| ランダムアクセス | O(1) - 高速 | O(n) - 遅い |
| 先頭への挿入 | O(n) - 遅い | O(1) - 高速 |
| 末尾への挿入 | O(1) - 高速 | O(1) - 高速 |
| 中間への挿入 | O(n) - 遅い | O(n) - 中程度 |
| メモリ使用量 | 少ない | 多い（リンクの分） |

**使い分け**:
- **ArrayList**: ランダムアクセスが多い場合、末尾への追加が多い場合
- **LinkedList**: 先頭・中間への挿入・削除が多い場合

#### HashMapとTreeMapの違いは？

**HashMap**: ハッシュテーブルを使用。順序は保証されない。高速
**TreeMap**: 赤黒木を使用。キーでソートされる。やや遅い

**HashMapの例**:
```java
import java.util.HashMap;
import java.util.Map;

Map<String, Integer> map = new HashMap<>();
map.put("りんご", 100);
map.put("バナナ", 80);
map.put("オレンジ", 120);

System.out.println(map);  
// {バナナ=80, りんご=100, オレンジ=120}（順序は保証されない）

// 検索が高速（O(1)）
Integer price = map.get("りんご");  // 100
```

**TreeMapの例**:
```java
import java.util.TreeMap;
import java.util.Map;

Map<String, Integer> map = new TreeMap<>();
map.put("りんご", 100);
map.put("バナナ", 80);
map.put("オレンジ", 120);

System.out.println(map);  
// {オレンジ=120, バナナ=80, りんご=100}（キーでソートされる）

// 検索がやや遅い（O(log n)）
Integer price = map.get("りんご");  // 100
```

**比較表**:

| 項目 | HashMap | TreeMap |
|------|---------|---------|
| 順序 | 保証されない | キーでソートされる |
| 検索速度 | O(1) - 高速 | O(log n) - やや遅い |
| nullキー | 許可（1つまで） | 許可されない |
| 実装 | ハッシュテーブル | 赤黒木 |
| 使用場面 | 順序が不要な場合 | ソートが必要な場合 |

**使い分け**:
- **HashMap**: 順序が不要で、高速な検索が必要な場合
- **TreeMap**: キーでソートしたい場合、範囲検索が必要な場合

---

### 10. ラムダ式とストリームAPI

#### ラムダ式とは何か？

**ラムダ式**は、関数を簡潔に記述するための構文です。Java 8で導入されました。

**従来の書き方（匿名内部クラス）**:
```java
List<String> list = Arrays.asList("りんご", "バナナ", "オレンジ");

// 匿名内部クラスでComparatorを実装
Collections.sort(list, new Comparator<String>() {
    @Override
    public int compare(String s1, String s2) {
        return s1.length() - s2.length();
    }
});
```

**ラムダ式での書き方**:
```java
List<String> list = Arrays.asList("りんご", "バナナ", "オレンジ");

// ラムダ式で簡潔に記述
Collections.sort(list, (s1, s2) -> s1.length() - s2.length());
```

**ラムダ式の構文**:
```java
(引数) -> { 処理 }
```

**具体例**:
```java
// 引数が1つの場合
List<String> list = Arrays.asList("りんご", "バナナ");
list.forEach(s -> System.out.println(s));

// 引数が複数の場合
list.sort((s1, s2) -> s1.compareTo(s2));

// 処理が複数行の場合
list.forEach(s -> {
    System.out.print("フルーツ: ");
    System.out.println(s);
});
```

#### ストリームAPIの利点は？

**ストリームAPI**は、コレクションの要素を関数型のスタイルで処理するためのAPIです。

**従来の書き方**:
```java
List<String> list = Arrays.asList("りんご", "バナナ", "オレンジ", "ぶどう");
List<String> result = new ArrayList<>();

// 3文字以上の要素を大文字に変換して新しいリストに追加
for (String s : list) {
    if (s.length() >= 3) {
        result.add(s.toUpperCase());
    }
}
```

**ストリームAPIでの書き方**:
```java
List<String> list = Arrays.asList("りんご", "バナナ", "オレンジ", "ぶどう");

List<String> result = list.stream()
    .filter(s -> s.length() >= 3)      // 3文字以上をフィルタ
    .map(s -> s.toUpperCase())          // 大文字に変換
    .collect(Collectors.toList());      // リストに収集
```

**ストリームAPIの利点**:
1. **可読性**: 処理の流れが明確
2. **簡潔性**: コードが短くなる
3. **関数型スタイル**: 副作用が少ない
4. **並列処理**: `parallelStream()`で簡単に並列化できる

#### filter、map、reduceの役割は？

**`filter`**: 条件に合う要素だけを抽出
**`map`**: 各要素を変換
**`reduce`**: 要素を集約して1つの値にまとめる

**filterの例**:
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

// 偶数だけを抽出
List<Integer> evens = numbers.stream()
    .filter(n -> n % 2 == 0)
    .collect(Collectors.toList());

System.out.println(evens);  // [2, 4, 6, 8, 10]
```

**mapの例**:
```java
List<String> words = Arrays.asList("apple", "banana", "orange");

// 各文字列の長さに変換
List<Integer> lengths = words.stream()
    .map(s -> s.length())
    .collect(Collectors.toList());

System.out.println(lengths);  // [5, 6, 6]

// 大文字に変換
List<String> upper = words.stream()
    .map(s -> s.toUpperCase())
    .collect(Collectors.toList());

System.out.println(upper);  // [APPLE, BANANA, ORANGE]
```

**reduceの例**:
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

// 合計を計算
int sum = numbers.stream()
    .reduce(0, (a, b) -> a + b);

System.out.println(sum);  // 15

// 最大値を取得
int max = numbers.stream()
    .reduce(Integer.MIN_VALUE, (a, b) -> a > b ? a : b);

System.out.println(max);  // 5
```

**組み合わせた例**:
```java
List<Person> people = Arrays.asList(
    new Person("田中", 25),
    new Person("佐藤", 30),
    new Person("鈴木", 20)
);

// 20歳以上の人の名前を大文字にして、カンマ区切りで結合
String result = people.stream()
    .filter(p -> p.getAge() >= 20)           // 20歳以上をフィルタ
    .map(p -> p.getName().toUpperCase())    // 名前を大文字に変換
    .reduce("", (a, b) -> a.isEmpty() ? b : a + ", " + b);  // 結合

System.out.println(result);  // TANAKA, SATO, SUZUKI
```

**その他の便利なメソッド**:
```java
// ソート
list.stream().sorted().collect(Collectors.toList());

// 重複を除去
list.stream().distinct().collect(Collectors.toList());

// 要素数をカウント
long count = list.stream().count();

// 最大値・最小値
Optional<String> max = list.stream().max(String::compareTo);
Optional<String> min = list.stream().min(String::compareTo);

// 全ての要素が条件を満たすか
boolean allMatch = list.stream().allMatch(s -> s.length() > 3);

// いずれかの要素が条件を満たすか
boolean anyMatch = list.stream().anyMatch(s -> s.length() > 3);
```

---

この解答編は、Java学習ガイドの理解度チェックリストに対する詳細な説明です。
各概念をコード例と共に理解することで、実践的なJavaプログラミングの基礎を身につけることができます。
