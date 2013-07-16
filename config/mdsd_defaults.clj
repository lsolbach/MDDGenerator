{:std-generators
 [{:element "Class"
   :template "uml14/mof/element-interface"
   :includes ["lib" "model/lib" "java/lib" "java/interface" "uml14/lib"]
   :subdir "src"
   :extension "java"
   :namespaceReplacement "org.soulspace.modelling.uml14.elements"
   :stereotypes "NONE"
   }
  {:element "Class"
   :template "uml14/mof/element-class"
   :includes ["lib" "model/lib" "java/lib" "java/class" "uml14/lib"]
   :subdir "src"
   :prefix "Abstract"
   :extension "java"
   :namespaceReplacement "org.soulspace.modelling.uml14.elements.impl"
   :stereotypes "NONE"
   }
  {:element "Class"
   :template "uml14/mof/element-concrete-class"
   :includes ["lib" "model/lib" "java/lib" "java/class" "uml14/lib"]
   :subdir "src"
   :suffix "Impl"
   :extension "java"
   :namespaceReplacement "org.soulspace.modelling.uml14.elements.impl"
   :stereotypes "NONE"
   }
  {:element "Class"
   :template "uml14/mof/enum"
   :includes ["lib" "model/lib" "java/lib" "java/class" "uml14/lib"]
   :subdir "src"
   :extension "java"
   :namespaceReplacement "org.soulspace.modelling.uml14.types"
   :stereotypes "enumeration"
   }
  {:element "Class"
   :template "uml14/mof/primitive"
   :includes ["lib" "model/lib" "java/lib" "java/class" "uml14/lib"]
   :subdir "src"
   :extension "java"
   :namespaceReplacement "org.soulspace.modelling.uml14.primitives"
   :stereotypes "primitive"
   }
  {:element "Model"
   :template "uml14/mof/repository"
   :includes ["lib" "model/lib" "java/lib" "java/class" "uml14/lib"]
   :subdir "src"
   :baseName "Uml14RepositoryImpl"
   :extension "java"
   :namespaceReplacement "org.soulspace.modelling.uml14.impl"
   }
  {:element "Model"
   :template "uml14/xmi/1.2/schema"
   :includes ["lib" "model/lib"]
   :subdir "uml"
   :basename "uml14_xmi12"
   :extension "xsd"
   }
  {:element "Model"
   :template "uml14/xmi/1.2/reader"
   :includes ["lib" "model/lib" "java/lib" "java/class" "uml14/lib"]
   :subdir "src"
   :baseName "Xmi12ReaderImpl"
   :extension "java"
   :namespaceReplacement "org.soulspace.modelling.uml14.impl"
   }
  {:element "Model"
   :template "uml14/xmi/1.2/writer"
   :includes ["lib" "model/lib" "java/lib" "java/class" "uml14/lib"]
   :subdir "src"
   :baseName "Xmi12WriterImpl"
   :extension "java"
   :namespaceReplacement "org.soulspace.modelling.uml14.impl"
   }
  {:element "Model"
   :template "uml14/xmi/1.2/clj-dsl"
   :includes ["lib" "model/lib"]
   :subdir "clj"
   :basename "xmi12"
   :extension "clj"
   }
  {:element "Model"
   :template "uml14/xmi/1.2/clj-uml14-dsl"
   :includes ["lib" "model/lib"]
   :subdir "clj"
   :basename "uml14"
   :extension "clj"
   }]
 }