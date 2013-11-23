[
 :module "UML14ModelBuilder"
 :project "org.soulspace.modelling"
 :type :library
 :version "0.5.0"
 :description "A builder for generator models from UML 1.4/XMI 1.2 models"
 :provider "soulspace.org"
 :plugins ["global" "dependencies" "mdsd" "java" "package" "release"]
 :dependencies [[["org.soulspace.modelling" "UML14Repository" "0.5.0"]]
                [["org.soulspace.modelling" "MDDRepository" "0.5.0"]]
                ;[["org.soulspace.modelling" "MDDRepository" "0.5.0" "MDDRepository" "xmi"] :model]
                [["org.soulspace.modelling" "ArgoUMLProfileLibrary" "1.0.0" "ArgoUMLProfileLibrary" "zip"] :generator]
                [["org.soulspace.modelling" "MDDTemplateLibrary" "1.0.0" "MDDTemplateLibrary" "zip"] :generator]
                [["junit" "junit" "3.8.1"] :dev]]
 :mdsd-config-dir "config"
 :mdsd-template-path "${lib-generator-dir}/templates:templates"
 :mdsd-std-profiles ["argouml/default-uml14"]
 :mdsd-model-dir "../MDDRepository/model/"
 :mdsd-model-name "MDDRepository"
 :log-level :debug
 :debug "true"
 ]
