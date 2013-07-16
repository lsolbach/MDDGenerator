[
 :name "UML14Repository"
 :project "org.soulspace.modelling"
 :type "library"
 :version "0.3.0"
 :description "The UML14Repository provides a repository for UML 1.4 models and importers/exporters for XMI 1.2 files"
 :vendor "soulspace.org"
 :plugins ["global" "deps" "mdsd" "java" "package"]
 :dependencies [["junit" "junit" "3.8.1" "dev"]
                ["org.soulspace.modelling" "ArgoUMLProfileLibrary" "1.0.0" "generator" "ArgoUMLProfileLibrary" "zip"]
                ["org.soulspace.modelling" "MDDTemplateLibrary" "1.0.0" "generator" "MDDTemplateLibrary" "zip"]]
 :mdsd-config-dir "config"
 :mdsd-template-path "${lib-generator-dir}/templates,templates"
 :mdsd-std-profiles ["argouml/default-uml14"]
 :log-level "debug"
 :debug "true"
 ]
