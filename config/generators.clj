{:standard-generators
 [{:element "Class"
   :template "java/interface"
   :includes ["lib" "model/lib" "java/lib" "java/interface" "java/bean-interface" "uml14/lib" "uml14/mof/element-interface"]
   :subdir "src"
   :extension "java"
   :baseNamespace "org.soulspace.modelling.uml14.elements"
   :stereotypes "NONE"
   }
  {:element "Class"
   :template "java/class"
   :includes ["lib" "model/lib" "java/lib" "java/class" "java/bean-class" "uml14/lib" "uml14/mof/element-class"]
   :subdir "src"
   :prefix "Abstract"
   :extension "java"
   :baseNamespace "org.soulspace.modelling.uml14.elements.impl"
   :stereotypes "NONE"
   }
  {:element "Class"
   :template "java/class"
   :includes ["lib" "model/lib" "java/lib" "java/class" "java/impl-class" "uml14/lib" "uml14/mof/element-concrete-class"]
   :subdir "src"
   :suffix "Impl"
   :extension "java"
   :baseNamespace "org.soulspace.modelling.uml14.elements.impl"
   :stereotypes "NONE"
   }
  {:element "Class"
   :template "uml14/mof/enum"
   :includes ["lib" "model/lib" "java/lib" "java/class" "uml14/lib"]
   :subdir "src"
   :extension "java"
   :baseNamespace "org.soulspace.modelling.uml14.types"
   :stereotypes "enumeration"
   }
  {:element "Class"
   :template "uml14/mof/primitive"
   :includes ["lib" "model/lib" "java/lib" "java/class" "uml14/lib"]
   :subdir "src"
   :extension "java"
   :baseNamespace "org.soulspace.modelling.uml14.primitives"
   :stereotypes "primitive"
   }
  {:element "Model"
   :template "uml14/mof/repository"
   :includes ["lib" "model/lib" "java/lib" "java/class" "uml14/lib"]
   :subdir "src"
   :baseName "Uml14RepositoryImpl"
   :extension "java"
   :baseNamespace "org.soulspace.modelling.uml14.impl"
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
   :baseNamespace "org.soulspace.modelling.uml14.impl"
   }
  {:element "Model"
   :template "uml14/xmi/1.2/writer"
   :includes ["lib" "model/lib" "java/lib" "java/class" "uml14/lib"]
   :subdir "src"
   :baseName "Xmi12WriterImpl"
   :extension "java"
   :baseNamespace "org.soulspace.modelling.uml14.impl"
   }
  {:element "Model"
   :template "uml14/xmi/1.2/clj-dsl"
   :includes ["lib" "model/lib"]
   :subdir "clj"
   :baseName "xmi12"
   :extension "clj"
   }
  {:element "Model"
   :template "uml14/xmi/1.2/clj-uml14-dsl"
   :includes ["lib" "model/lib"]
   :subdir "clj"
   :baseName "uml14"
   :extension "clj"
   }]
 }