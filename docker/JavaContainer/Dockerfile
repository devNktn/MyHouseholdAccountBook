FROM openjdk:11.0.15-jdk-oraclelinux7

ENV LANG ja_JP.UTF-8

# コンテナ起動時に実行するシェルスクリプトを追加
ADD start.sh  /

# start.shを実行し、コンテナが落ちないようにする
RUN  chmod +x /start.sh
CMD  ["/start.sh"]