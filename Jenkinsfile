pipeline {
    agent {
        docker {
            image 'maven:3.3.9-jdk-8'
            args '-v /home/jenkins/.m2:/root/.m2'
        }
    }
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
                sh 'mvn sonar:sonar'
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
            }
            steps {
                publishHtml {
                    reportName 'Mutation Testing'
                    reportFiles 'index.html'
                    reportDir 'target/pit-reports/**/*'
                    allowMissing true
                }
            }
            archiveArtifacts 'target/*.jar'
        }
    }
}