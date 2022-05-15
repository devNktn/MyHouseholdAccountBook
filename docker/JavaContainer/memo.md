# 独り言メモ

## 2022/05/13

とりあえず、Javaは11でビルドツールでGradleを使おうと思う。

（Gradleも勉強が中途半端だったので、慣れておきたい。）

というわけで、Java側の開発環境については、以下のバージョンの組み合わせで行く。
事前に動くことがわかっている。

  - OpenJDK 11
  - Gradle 7.4.2
  - SpringBoot 

Gradleのコンテナもあるみたいだけど、Openjdk11のコンテナを使おうと思う。

Dockerfileに慣れたいので、OpenjdkのコンテナにCOPYでGradleのzipファイルをおいて、解凍＆パス通せるか試してみる。

### openjdk 11のイメージを使って、最低限のDockerfileを作る

-> できた。

Javaのバージョンは、Dockerhubにあった11の最新版の11.0.15にした。


![最低限のDockerfileと確認](./ss/%E3%82%B9%E3%82%AF%E3%83%AA%E3%83%BC%E3%83%B3%E3%82%B7%E3%83%A7%E3%83%83%E3%83%88%202022-05-13%20225521.png)

### コンテナを起動したままにする

docker runだけでは落ちてしまうため、docker -itd xxx:yyy /bin/shを入れているが、面倒。

docker runだけでも落ちなくなるよう、シェルスクリプトを用意してDockerfileでコピー＆実行してみる。

-> できた。

tail -f /dev/nullを実行するシェルスクリプトを用意して、Dockerfileを編集した。

![コンテナが起動したままになる確認](./ss/%E3%82%B9%E3%82%AF%E3%83%AA%E3%83%BC%E3%83%B3%E3%82%B7%E3%83%A7%E3%83%83%E3%83%88%202022-05-13%20231450.png)

### Gradleをコンテナにインストールする

Gradleのzipを用意して、それをDockerfileでコピー＆解凍する。

その後、パスを通して、gradleコマンドが叩けるか確認する。

-> できた。

![gradleがインストールされている確認](./ss/%E3%82%B9%E3%82%AF%E3%83%AA%E3%83%BC%E3%83%B3%E3%82%B7%E3%83%A7%E3%83%83%E3%83%88%202022-05-14%20011530.png)

### SpringBootプロジェクトをコンテナにマウントする

SpringBoot initializerでプロジェクトを作成してみた。お手軽でいい感じ。

![SpringBoot initializerの設定](./ss/%E3%82%B9%E3%82%AF%E3%83%AA%E3%83%BC%E3%83%B3%E3%82%B7%E3%83%A7%E3%83%83%E3%83%88%202022-05-14%20015203.png)

Dependenciesは以下を追加

- Spring Boot DevTools
- Lombok
- Spring Web
- Thymeleaf
- Spring Data JPA
- MySQL Driver

ダウンロードしたkakeibo.zipを展開し、Dockerfileに記載してvolumeマウントする。

docker-compose.ymlを作成する。

-> できた。

### unzipをオフラインでダウンロード

Dockerfileのビルドの旅にunzipをネットからインストールするのが時間とネット回線の無駄なので、それを解消する。

-> できた。

参考URL: [オフライン環境でのrpmインストール
](https://tokku-engineer.tech/offline-rpm-install/)

ちなみに、start.shで起動したままにするだけのDockerfileに戻し、起動後にexec -it XXXXXXXX /bin/bashで中に入って以下のコマンドでRPMファイルを作成した。

yum install unzip --downloadonly --downloaddir=$(pwd)

ダウンロードしたunzip-6.0-24.el7_9.x86_64.rpmをローカルのtoolディレクトリに入れて、DockerfileでCOPYとインストールを指定。

## 2022/05/14

### Remote - Containersの設定

VSCodeでは、コンテナに入って直接操作できるRemote - Containersという拡張機能があるらしい。

それを使ってコンテナに入ればよさそう。

-> できた。

具体的には、docker-compose upした後でRemote - Containersを開くと、稼働中のコンテナが出てきた。

そちらに接続すると、任意コード実行の確認が出てきて、OKを押すと接続できた。

変更もリアルタイムでマウントしたローカルフォルダに反映されるし、いい感じ。

また、それとは別にdocker-composeを使ってDockerfileでビルドしたイメージの名前とバージョン（タグ）を指定できないか？と調べてみたら、imageにイメージ名:タグ名を入れればいけそう。

-> できた。

![docker-composeによるイメージ名:タグ名の指定](./ss/%E3%82%B9%E3%82%AF%E3%83%AA%E3%83%BC%E3%83%B3%E3%82%B7%E3%83%A7%E3%83%83%E3%83%88%202022-05-14%20222328.png)

### Gradleのビルド

コンテナに入って、gradle bootRunをやってみた。

-> 失敗。gradle buildも失敗。

エラーを眺めていて、MySQLサーバを用意していないからでは？と原因の検討はついたが、ここは基礎からやり直す。

スモールステップでいきたいので、いったん素の状態のSpringBootプロジェクトにして、Hello Worldからやっていく。

その他の拡張機能は、開発しつつ追加していくことで、どの拡張機能がどんなことをしてくれるのか、実感を持って理解できると考えた。

### Spring BootのHello World

Spring Initializerで、Dependencyに何も選ばないでプロジェクトを作成した。

そこで作成したプロジェクトのbuild.gradleと同じになるように、既存のbuild.gradleに追記・コメントアウトをした。

 -> gradle buildは通るようになった。

 続いて、以下のURLを参考に、Hello Worldを作成する。(名称など一部修正したが)

[SpringBoot で Hello World](https://qiita.com/t-iguchi/items/c1fd78de3b2961d65761)

 -> 試しにbuild.gradleを何も変えない場合、gradle bootRunの時点でエラーが出る。
 
 -> implementation 'org.springframework.boot:spring-boot-starter-web'を有効にしたら、bootRun時点のエラーはなくなるが、http://localhost:8080にアクセスしてもWhitePageエラー（404）

 -> implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'を有効にしたら、画面が表示される。

 ### v0.10のリリース

 とりあえず、Javaの開発環境は作れたので、これをv0.10としてリリース。

### MySQLのDockerfile作成

以下のURLが参考になった。なお、バージョンは8.0.22にする。
[サンプルデータがあらかじめ入った MySQL を Docker で作成する](https://www.xlsoft.com/jp/blog/blog/2019/10/09/post-7617/)

Dockerfileとdocker-compose.ymlで、どちらでどこまで設定するかの思想は人それぞれありそう。

個人的には、言語設定やルートユーザのパスワードはDockerfile、そのほかの設定はdocker-compose.ymlとしたらわかりやすい。

 -> できた。

サンプルとして、参考URLの01_init_db.sqlや02_insert_data.sqlをそのまま使った。

確認は、ホストPCのWindows10にもともとインストールしてあったMySQL Workbenchでした。

MySQLのコンテナは以外とあっさり構築できた。

### v0.20のリリース

MySQLの開発環境は作れたので、これをv0.20としてリリース。

## 2022/05/15

### docker-composeの統合

JavaContainerとMySQLContainerのdocker-compose.ymlをまとめる。

スモールステップで、まず目標は2つのコンテナとネットワークが起動するところまで。

普通にまとめればいけそうな気がする。やったことは以下に記載。

- 2つのdocker-compose.ymlをまとめて、重複するversion:3とservice:を削除
- MySQLのサービス名を「mysql」から「mysql-server」に変更（ケアレスミスをついでに修正）
- java-containerにdepends_on: mysql-serverを追記
- フォルダやファイルの指定について、相対パスを修正
- ネットワーク（kakeibo-net）を作って、そちらに接続

### MySQLについて、ユーザnktnに権限を付与するシェルスクリプトを追加

MySQLの初期スクリプトでサンプルとして作っているjpaddressスキーマに、ユーザnktnではアクセスできないことが気になっていた。

そこで、ユーザnktnに権限を設定するシェルスクリプトを追加してみる。

 -> できた。MySQLContainer/initに01_give_full_privileges_to_mysql_user.shを追加した。
