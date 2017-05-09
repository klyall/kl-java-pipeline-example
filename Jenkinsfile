pipeline {
    agent { docker 'maven:3.3.9-jdk-8' }
    environment {
        MAVEN_OPTS = '-Djava.awt.headless=true -Dhttp.proxySet=true -Dhttps.proxySet=true -Dhttp.useProxy=true -Dhttps.useProxy=true -Djava.net.useSystemProxies=true -Dhttp.proxyHost=172.16.0.40 -Dhttp.proxyPort=3128 -Dhttps.proxyHost=172.16.0.40 -Dhttps.proxyPort=3128 -Dhttp.nonProxyHosts=*tfc.sicloud.atos.net'
        JAVA_OPTS = '-Djava.awt.headless=true -Dhttp.proxySet=true -Dhttps.proxySet=true -Dhttp.useProxy=true  -Dhttps.useProxy=true -Djava.net.useSystemProxies=true -Dhttp.proxyHost=172.16.0.40 -Dhttp.proxyPort=3128 -Dhttps.proxyHost=172.16.0.40 -Dhttps.proxyPort=3128 -Dhttp.nonProxyHosts=nexus-tfc.sicloud.atos.net'
    }
    stages {
        stage('Clean') {
            steps {
                sh 'mvn --version'
                sh 'java -version'
                sh 'mvn clean'
            }
        }
        stage('Unit Test'){
            steps {
                sh 'mvn test'
            }
        }
        stage('Report'){
            steps {
                junit 'target/surefire-reports/**/*.xml'
            }
        }
    }
}