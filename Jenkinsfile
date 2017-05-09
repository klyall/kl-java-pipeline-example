pipeline {
    agent {
        docker {
            image 'maven:3.3.9-jdk-8'
            args '-v /home/jenkins/.m2:/root/.m2'
        }
    }
    environment {
        MAVEN_OPTS = "${env.JAVA_OPTS}"
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
        stage('Unit Test'){
            steps {
                sh 'mvn verify'
            }
        }
        stage('Mutation Test'){
            steps {
                sh 'mvn org.pitest:pitest-maven:mutationCoverage'
            }
        }
        stage('Static Analysis'){
            steps {
                sh "mvn sonar:sonar  -Dsonar.host.url=${env.SONAR_URL}"
            }
        }
        stage('Publish'){
            steps {
                sh 'mvn install -Dmaven.test.skip=true'
            }
        }
    }
    post {
        always {
            junit 'target/surefire-reports/**/*.xml'
            archiveArtifacts 'target/*.jar'
            publishHTML (target: [
                 allowMissing: true
                 reportDir 'target/pit-reports/**/*'
                 reportFiles 'index.html'
                 reportName 'Mutation Testing'
            ])
            publishHTML (target: [
                  allowMissing: false,
                  alwaysLinkToLastBuild: false,
                  keepAll: true,
                  reportDir: 'coverage',
                  reportFiles: 'index.html',
                  reportName: "RCov Report"
            ])
        }
    }
}