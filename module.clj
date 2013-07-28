[
 :module "MDDGenerator"
 :project "org.soulspace.modelling"
 :project-lead "Ludger Solbach"
 :provider "soulspace.org"
 :type :framework
 :version "0.3.0"
 :description "Generator framework for model driven software development. Contains generators and ant tasks."
 :license ["Eclipse Public License 1.0" "http://www.eclipse.org/legal/epl-v10.html"]
 :plugins ["global" "sdeps" "mdsd" "java" "package"]
 :dependencies [[["org.soulspace.template" "TemplateEngine" "1.0.0"]]
                [["org.soulspace.common" "JavaUtilLibrary" "0.3.0"]]
                [["org.soulspace.modelling" "ArgoUMLProfileLibrary" "1.0.0" "ArgoUMLProfileLibrary" "zip"] :generator]
                [["org.soulspace.modelling" "MDDTemplateLibrary" "1.0.0" "MDDTemplateLibrary" "zip"] :generator]
;                [["org.soulspace.modelling" "MDDRepository" "0.3.0" "MDDRepository" "xmi"] "model"]
                [["org.soulspace.modelling" "MDDRepository" "0.3.0"]]
                [["org.soulspace.modelling" "UML14Repository" "0.3.0"]]
                [["org.soulspace.modelling" "UML14ModelBuilder" "0.3.0"]]
                [["oro" "oro" "2.0.8"]]
                [["org.apache.ant" "ant" "1.8.3"] "dev"]
                [["org.apache.ant" "ant-launcher" "1.8.3"] :dev]
                [["org.apache.ant" "ant-testutil" "1.8.3"] :dev]
                [["junit" "junit" "3.8.1"] :dev]]
 :mdsd-config-dir "config"
 :mdsd-config-file "generators.clj"
 :mdsd-template-path "${lib-generator-dir}/templates:templates"
 :mdsd-model-dir "../MDDRepository/model"
 :mdsd-model-name "MDDRepository"
 :mdsd-std-profiles ["argouml/default-uml14"]
 :log-level :debug
 ]
