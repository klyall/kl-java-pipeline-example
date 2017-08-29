pipeline {
    agent {
        docker {
            image 'maven:3.5-jdk-8'
            args '-v /var/local/maven:/var/maven'
        }
    }
    options {
        // Keep the 10 most recent builds
        buildDiscarder(logRotator(numToKeepStr:'10'))
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
                sh 'cat /etc/resolv.conf'
                sh 'wget ${NEXUS_HOST}/nexus/'
                sh 'wget ${SONAR_HOST}/'
            }
        }
        stage('Test'){
            steps {
                sh 'mvn verify -P coverage'
                junit '**/target/surefire-reports/*.xml'
            }
        }
        stage('Mutation Test'){
            steps {
                sh 'mvn org.pitest:pitest-maven:mutationCoverage -DtimestampedReports=false'
            }
        }
        stage('Static Analysis'){
            steps {
                sh "mvn install -Dmaven.test.failure.ignore=true -P coverage sonar:sonar -Dsonar.pitest.mode=reuseReport"
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
