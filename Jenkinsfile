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
        parallel A: {
        },
        B: {
        },
        C: {
        }

        stage('Unit Test'){
            steps {
                sh 'mvn verify'
            }
        }
        stage('Mutation Test'){
            steps {
                sh 'mvn org.pitest:pitest-maven:mutationCoverage -DtimestampedReports=false'
            }
        }
        stage('Static Analysis'){
            steps {
                sh "mvn sonar:sonar -Dsonar.host.url=${env.SONAR_URL}"
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
            PitPublisher (target: [
                mutationStatsFile: 'target/pit-reports/mutations.xml',
                minimumKillRatio: 50.00,
                killRatioMustImprove: true
            ])
            publishHTML (target: [
                 allowMissing: true,
                 alwaysLinkToLastBuild: true,
                 keepAll: true,
                 reportDir: 'target/pit-reports',
                 reportFiles: 'index.html',
                 reportName: 'PIT Report'
            ])
            publishHTML (target: [
                  allowMissing: false,
                  alwaysLinkToLastBuild: false,
                  keepAll: true,
                  reportDir: 'target/coverage/unit-tests',
                  reportFiles: 'index.html',
                  reportName: "Unit Testing Coverage Report"
            ])
            publishHTML (target: [
                  allowMissing: false,
                  alwaysLinkToLastBuild: false,
                  keepAll: true,
                  reportDir: 'target/coverage/integration-tests',
                  reportFiles: 'index.html',
                  reportName: "Integration Testing Coverage Report"
            ])        }
    }
}