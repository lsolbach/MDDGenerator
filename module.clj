[
 :name "UML14ModelBuilder"
 :project "org.soulspace.modelling"
 :type "library"
 :version "0.3.0"
 :description "A builder for generator models from UML 1.4/XMI 1.2 models"
 :vendor "soulspace.org"
 :plugins ["global" "deps" "mdsd" "java" "package"]
 :dependencies [["org.soulspace.modelling" "UML14Repository" "0.3.0"]
                ["org.soulspace.modelling" "ModelRepository2" "0.3.0"]
                ["org.soulspace.modelling" "ArgoUMLProfileLibrary" "1.0.0" "generator" "ArgoUMLProfileLibrary" "zip"]
                ["org.soulspace.modelling" "MDDTemplateLibrary" "1.0.0" "generator" "MDDTemplateLibrary" "zip"]
                ["junit" "junit" "3.8.1" "dev"]]
 :mdsd-config-dir "config"
 :mdsd-template-path "${lib-generator-dir}/templates,templates"
 :mdsd-std-profiles ["argouml/default-uml14"]
 :log-level "debug"
 :debug "true"
 ]
