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
   :template "java/class"
   :includes ["lib" "model/lib" "java/lib" "java/class" "uml14/lib" "uml14/mof/primitive"]
   :subdir "src"
   :extension "java"
   :baseNamespace "org.soulspace.modelling.uml14.primitives"
   :stereotypes "primitive"
   }
  {:element "Class"
   :template "uml14/mof/enum"
   :includes ["lib" "model/lib" "java/lib" "java/class" "uml14/lib"]
   :subdir "src"
   :extension "java"
   :baseNamespace "org.soulspace.modelling.uml14.types"
   :stereotypes "enumeration"
   }
  {:element "Model"
   :template "uml14/mof/repository" ; TODO switch to java/class?
   :includes ["lib" "model/lib" "java/lib" "java/class" "uml14/lib"]
   :subdir "src"
   :baseNamespace "org.soulspace.modelling.uml14.impl"
   :baseName "Uml14RepositoryImpl"
   :extension "java"
   }
  {:element "Model"
   :template "uml14/xmi/1.2/schema" ; TODO use xml/xml with xml/schema include?
   :includes ["lib" "model/lib" "uml14/lib"]
   :subdir "uml"
   :baseName "uml14_xmi12"
   :extension "xsd"
   }
  {:element "Model"
   :template "uml14/xmi/1.2/reader" ; TODO switch to java/class?
   :includes ["lib" "model/lib" "java/lib" "java/class" "uml14/lib"]
   :subdir "src"
   :baseName "Xmi12ReaderImpl"
   :extension "java"
   :baseNamespace "org.soulspace.modelling.uml14.impl"
   }
  {:element "Model"
   :template "uml14/xmi/1.2/writer" ; TODO switch to java/class?
   :includes ["lib" "model/lib" "java/lib" "java/class" "uml14/lib"]
   :subdir "src"
   :baseName "Xmi12WriterImpl"
   :extension "java"
   :baseNamespace "org.soulspace.modelling.uml14.impl"
   }]
 }