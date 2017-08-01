pipeline {
    agent {
        docker {
            image 'maven:3.5-jdk-8'
            args '-v /var/local/maven:/var/maven'
        }
    }
    environment {
        MAVEN_CONFIG = "/var/maven/.m2"
        MAVEN_OPTS = "-Duser.home=/var/maven ${env.JAVA_OPTS}"
        JAVA_TOOL_OPTIONS = "${env.JAVA_OPTS}"
    }
    stages {
        stage('Clean') {
            steps {
                sh 'env | sort'
                sh 'mvn --version'
                sh 'java -version'
                sh 'mvn clean'
            }
        }
        stage('Test'){
            steps {
                sh 'mvn verify -P coverage'
                junit 'target/surefire-reports/**/*.xml'
                publishHTML (target: [
                      allowMissing: false,
                      alwaysLinkToLastBuild: false,
                      keepAll: true,
                      reportDir: '**/target/coverage/unit-tests',
                      reportFiles: 'index.html',
                      reportName: "Unit Testing Coverage Report"
                ])
                publishHTML (target: [
                      allowMissing: false,
                      alwaysLinkToLastBuild: false,
                      keepAll: true,
                      reportDir: '**/target/coverage/integration-tests',
                      reportFiles: 'index.html',
                      reportName: "Integration Testing Coverage Report"
                ])
            }
        }
        stage('Mutation Test'){
            steps {
                sh 'mvn org.pitest:pitest-maven:mutationCoverage -DtimestampedReports=false'
                publishHTML (target: [
                     allowMissing: true,
                     alwaysLinkToLastBuild: true,
                     keepAll: true,
                     reportDir: '**/target/pit-reports',
                     reportFiles: 'index.html',
                     reportName: 'PIT Report'
                ])
            }
        }
        stage('Static Analysis'){
            steps {
                sh "mvn install -Dmaven.test.failure.ignore=true -P coverage sonar:sonar"
            }
        }
        stage('Publish'){
            steps {
                sh 'mvn clean deploy -Dmaven.test.skip=true'
                archiveArtifacts '**/target/*.jar'
            }
        }
    }
}
