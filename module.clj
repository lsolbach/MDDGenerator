[
 :module "ModelRepository2"
 :project "org.soulspace.modelling"
 :type :library
 :version "0.3.0"
 :description "A domain model for the generator framework"
 :vendor "soulspace.org"
 :plugins ["global" "sdeps" "mdsd" "java" "package"]
 :dependencies [[["junit" "junit" "3.8.1"] :dev]
                [["org.soulspace.modelling" "ArgoUMLProfileLibrary" "1.0.0" "ArgoUMLProfileLibrary" "zip"] :generator]
                [["org.soulspace.modelling" "MDDTemplateLibrary" "1.0.0" "MDDTemplateLibrary" "zip"] :generator]]
 :mdsd-config-dir "config"
 :mdsd-template-path "${lib-generator-dir}/templates:templates"
 :mdsd-std-profiles ["argouml/default-uml14"]
 :log-level :debug
 :debug "true"
 ]
