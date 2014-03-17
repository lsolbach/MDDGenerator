[
 :module "MDDGenerator"
 :project "org.soulspace.modelling"
 :project-lead "Ludger Solbach"
 :provider "soulspace.org"
 :type :framework
 :version "0.5.0"
 :description "Generator framework for model driven software development. Contains generators and ant tasks."
 :license ["Eclipse Public License 1.0" "http://www.eclipse.org/legal/epl-v10.html"]
 :plugins ["global"
           ["org.soulspace.baumeister/DependencyPlugin"]
           ["org.soulspace.baumeister/MDDGeneratorPlugin"]
           ["org.soulspace.baumeister/JavaPlugin"]
           ["org.soulspace.baumeister/JUnitPlugin"]
           ["org.soulspace.baumeister/PackagePlugin"]]
 :dependencies [["org.soulspace.template/TemplateEngine, 1.0.2"]
                ["org.soulspace.common/JavaUtilLibrary, 0.3.0"]
                ["org.soulspace.modelling/ArgoUMLProfileLibrary, 1.0.0, ArgoUMLProfileLibrary, zip" :generator]
                ["org.soulspace.modelling/MDDTemplateLibrary, 1.0.0, MDDTemplateLibrary, zip" :generator]
;                ["org.soulspace.modelling/MDDRepository, 0.5.0, MDDRepository, xmi" :model]
                ["org.soulspace.modelling/UML14ModelBuilder, 0.5.0"]
                ["org.apache.ant/ant, 1.8.3" :dev]
                ["org.apache.ant/ant-testutil, 1.8.3" :dev]
                ["junit/junit, 3.8.1" :dev]]
 :mddgenerator-config-dir "./config"
 :mddgenerator-template-path "${lib-generator-dir}/templates:templates"
 :mddgenerator-model-dir "../MDDRepository/model"
 :mddgenerator-model-name "MDDRepository"
 :mddgenerator-std-profiles ["argouml/default-uml14"]
 :log-level :info
 ]
