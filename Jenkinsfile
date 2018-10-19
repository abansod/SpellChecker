pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        sh 'echo "build"'
      }
    }
    stage('package') {
      steps {
        input 'should I proceed'
      }
    }
    stage('deploy') {
      steps {
        build(wait: true, job: 'deploy-ci')
      }
    }
  }
}