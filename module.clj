[
 :module "MDDRepository"
 :project "org.soulspace.modelling"
 :type :library
 :version "0.5.0"
 :description "The module MDDRepository contains the model repository of the MDD Generator Framework to facilitate code generation."
 :provider "soulspace.org"
 :plugins ["global" "dependencies" "mdsd" "java" "package" "release"]
 :dependencies [[["junit" "junit" "3.8.1"] :dev]
                [["org.soulspace.modelling" "ArgoUMLProfileLibrary" "1.0.0" "ArgoUMLProfileLibrary" "zip"] :generator]
                [["org.soulspace.modelling" "MDDTemplateLibrary" "1.0.0" "MDDTemplateLibrary" "zip"] :generator]]
 :mdsd-config-dir "./config"
; :mdsd-config-file "generators.clj"
 :mdsd-template-path "${lib-generator-dir}/templates:templates"
 :mdsd-std-profiles ["argouml/default-uml14"]
 :log-level :debug
 :debug "true"
 ]
