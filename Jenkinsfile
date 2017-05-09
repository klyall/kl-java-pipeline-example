pipeline {
    agent { docker 'maven:3.3.3' }
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
                junit 'reports/**/*.xml'
            }
        }
    }
}