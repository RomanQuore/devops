def call(){

    pipeline{

        agent any

        tools{
            nodejs 'NodeJS'
        }
      /*triggers {
        pollSCM('* * * * *') // Programa la verificación del repositorio cada minuto
    }*/
       environment{
           PROJECT = "${env.UrlGitHub}".replaceAll('.+/(.+)\\.git', '$1')toLowerCase()
       } 
        stages{
            
      
             stage('Fase 1: Construccion App') {
                steps {
                    script {
                        def buildapp = new org.devops.lb_buildartefacto()
                        buildapp.install()
                        def cloneapp = new org.devops.lb_buildartefacto()
                        cloneapp.clone()
                    }
                }
                
            }

         stage('Fase 1: Sonar Analisis'){
                  steps{
                    script{
                       def test = new org.devops.lb_analisissonarqube()
                       test.testCoverage()
                       def analisiSonar = new org.devops.lb_analisissonarqube()
                       analisiSonar.analisisSonar("${PROJECT}")
                       
                    }
                 }
           }

         
                   
        }
        
        }
}
