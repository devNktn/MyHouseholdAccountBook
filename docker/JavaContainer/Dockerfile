FROM openjdk:11.0.15-jdk-oraclelinux7

# 言語を
ENV LANG="ja_JP.UTF-8"

# コンテナ起動時に実行するシェルスクリプトを追加
ADD start.sh  /

# unzipコマンドのRPMファイルをコピー
COPY tool/unzip-6.0-24.el7_9.x86_64.rpm /tmp

# unzipコマンドをインストール
RUN rpm -ivh /tmp/unzip-6.0-24.el7_9.x86_64.rpm

# Gradleのzipファイルをコピー
COPY tool/gradle-7.4.2-bin.zip /tmp

# Gradleのファイルを展開
RUN unzip /tmp/gradle-7.4.2-bin.zip -d /opt/gradle

# GradleのPATHを通す
ENV GRADLE_HOME="/opt/gradle/gradle-7.4.2"
ENV PATH="$GRADLE_HOME/bin:$PATH"

# コピーしたファイルを削除
RUN rm /tmp/unzip-6.0-24.el7_9.x86_64.rpm
RUN rm /tmp/gradle-7.4.2-bin.zip

# start.shを実行し、コンテナが落ちないようにする
RUN  chmod +x /start.sh
CMD  ["/start.sh"]