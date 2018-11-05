
[![Build Status](https://travis-ci.org/kawakamimanabu/shainweb.svg?branch=master)](https://travis-ci.org/kawakamimanabu/shainweb)

# Java EE サンプルアプリケーション ShainWeb

Java EE 7 を用いた社員情報編集サンプルアプリケーションです。
DB は PostgreSQL を使用しています。

アプリケーションサーバは WildFly 9.0.2.Final を想定しています。

開発手順書は以下の URL を参照してください。

http://kawakamimanabu.github.io/shainweb/


## 単体テストのやり方

1. 環境変数 JBOSS_HOME を新規に追加して、WildFly のパスを設定します

1. PostgreSQL で shainwebtest データベースを追加してテーブルを作成します。

1. Maven で以下のコマンドを実行します。

    mvn clean test -Parq-wildfly-managed