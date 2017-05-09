pipeline {
    agent {
        docker {
            image 'maven:3.3.9-jdk-8'
            args '-v /home/jenkins/.m2:/root/.m2'
        }
    }
    environment {
        MAVEN_OPTS = "${env.JAVA_OPTS}""
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
        stage('Report'){
            steps {
                junit 'target/surefire-reports/**/*.xml'
                archiveArtifacts 'target/*.jar'
                publishHtml {
                     reportName 'Mutation Testing'
                     reportFiles 'index.html'
                     reportDir 'target/pit-reports/**/*'
                     allowMissing true
                }
            }
        }
    }
}