[
 :module "UML14Repository"
 :project "org.soulspace.modelling"
 :type :library
 :version "0.5.0"
 :description "The UML14Repository provides a repository for UML 1.4 models and importers/exporters for XMI 1.2 files"
 :provider "soulspace.org"
 :plugins ["global"
           ["org.soulspace.baumeister/MDDGeneratorPlugin"]
           ["org.soulspace.baumeister/JavaPlugin"]
           ["org.soulspace.baumeister/JUnitPlugin"]
           ["org.soulspace.baumeister/PackagePlugin"]]
 :dependencies [["junit/junit, 3.8.1" :dev]
                ["org.soulspace.modelling/ArgoUMLProfileLibrary, 1.0.0, ArgoUMLProfileLibrary, zip" :generator]
                ["org.soulspace.modelling/MDDTemplateLibrary, 1.0.0, MDDTemplateLibrary, zip" :generator]]
 :mddgenerator-config-dir "./config"
 :mddgenerator-template-path "${lib-generator-dir}/templates:templates"
 :mddgenerator-std-profiles ["argouml/default-uml14"]
 :compile-debug "true"
 ]
