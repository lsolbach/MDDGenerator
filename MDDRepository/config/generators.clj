{:standard-generators
 [{
   :element "Class"
   :template "java/interface"
   :includes ["lib" "model/lib" "java/lib" "java/interface" "java/bean-interface" "mdd/java/lib" "mdd/java/element-interface"]
   :subdir "src"
   :extension "java"
   :stereotypes "NONE"
   }
  {:element "Class"
   :template "java/class"
   :includes ["lib" "model/lib" "java/lib" "java/class" "java/bean-class" "mdd/java/lib" "mdd/java/element-class"]
   :subdir "src"
   :prefix "Abstract"
   :extension "java"
   :stereotypes "NONE"
   }
  {:element "Class"
   :template "java/class"
   :includes ["lib" "model/lib" "java/lib" "java/class" "java/impl-class" "java/bean-impl-class" "mdd/java/lib" "mdd/java/element-impl-class"]
   :subdir "tmp"
   :suffix "Impl"
   :extension "java"
   :namespaceSuffix "impl"
   :stereotypes "NONE"
   }
;  {:element "Class"
;   :template "mdd/java/element-impl-class"
;   :includes ["lib" "model/lib" "java/lib" "java/class" "mdd/java/lib"]
;   :subdir "tmp"
;   :suffix "Impl"
;   :extension "java"
;   :namespaceSuffix "impl"
;   :stereotypes "NONE"
;   }
  {:element "Model"
   :template "mdd/java/repository-interface"
   :includes ["lib" "model/lib" "java/lib" "java/interface" "mdd/java/lib"]
   :baseName "ModelRepository"
   :subdir "src"
   :extension "java"
   :baseNamespace "org.soulspace.modelling.repository"
   :stereotypes "NONE"
   }
  {:element "Model"
   :template "mdd/java/repository-class"
   :includes ["lib" "model/lib" "java/lib" "java/class" "mdd/java/lib"]
   :subdir "src"
   :baseName "ModelRepository"
   :suffix "Impl"
   :extension "java"
   :baseNamespace "org.soulspace.modelling.repository.impl"
   :stereotypes  "NONE"
   }]
 }