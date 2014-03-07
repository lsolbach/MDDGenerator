[
 :module "UML14ModelBuilder"
 :project "org.soulspace.modelling"
 :type :library
 :version "0.5.0"
 :description "A builder for generator models from UML 1.4/XMI 1.2 models"
 :provider "soulspace.org"
 :plugins ["global"
           ["org.soulspace.baumeister/DependencyPlugin"]
           ["org.soulspace.baumeister/MDDGeneratorPlugin"]
           ["org.soulspace.baumeister/JavaPlugin"]
           ["org.soulspace.baumeister/JUnitPlugin"]
           ["org.soulspace.baumeister/PackagePlugin"]]
 :dependencies [["org.soulspace.modelling/UML14Repository, 0.5.0"]
                ["org.soulspace.modelling/MDDRepository, 0.5.0"]
                ; ["org.soulspace.modelling/MDDRepository, 0.5.0, MDDRepository, xmi" :model]
                ["org.soulspace.modelling/ArgoUMLProfileLibrary, 1.0.0, ArgoUMLProfileLibrary, zip" :generator]
                ["org.soulspace.modelling/MDDTemplateLibrary, 1.0.1, MDDTemplateLibrary, zip" :generator]
                ["junit/junit, 3.8.1" :dev]]
 :mdsd-config-dir "config"
 :mdsd-template-path "${lib-generator-dir}/templates:templates"
 :mdsd-std-profiles ["argouml/default-uml14"]
 :mdsd-model-dir "../MDDRepository/model/"
 :mdsd-model-name "MDDRepository"
 :debug "true"
 ]
