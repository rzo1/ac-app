```bash
Index: container/openejb-core/src/main/java/org/apache/openejb/testing/SingleApplicationComposerBase.java
IDEA additional info:
Subsystem: com.intellij.openapi.diff.impl.patch.CharsetEP
<+>UTF-8
===================================================================
diff --git a/container/openejb-core/src/main/java/org/apache/openejb/testing/SingleApplicationComposerBase.java b/container/openejb-core/src/main/java/org/apache/openejb/testing/SingleApplicationComposerBase.java
--- a/container/openejb-core/src/main/java/org/apache/openejb/testing/SingleApplicationComposerBase.java	(revision e5c694c14939ff2542beeec7723fcdce2c7c1202)
+++ b/container/openejb-core/src/main/java/org/apache/openejb/testing/SingleApplicationComposerBase.java	(date 1716577184483)
@@ -16,13 +16,17 @@
  */
 package org.apache.openejb.testing;
 
+import jakarta.annotation.Resource;
 import org.apache.openejb.core.ThreadContext;
+import org.apache.openejb.loader.SystemInstance;
+import org.apache.openejb.spi.ContainerSystem;
 import org.apache.openejb.util.JavaSecurityManagers;
 import org.apache.webbeans.config.WebBeansContext;
 import org.apache.webbeans.inject.OWBInjector;
 import org.apache.xbean.finder.AnnotationFinder;
 import org.apache.xbean.finder.archive.FileArchive;
 
+import javax.naming.Context;
 import java.lang.reflect.Field;
 import java.util.Iterator;
 import java.util.concurrent.atomic.AtomicReference;
@@ -145,6 +149,33 @@
                     f.setAccessible(true);
                 }
                 f.set(target, app);
+            } else if (f.isAnnotationPresent(Resource.class)) {
+
+                try {
+                    if (!f.isAccessible()) {
+                        f.setAccessible(true);
+                    }
+
+                    final Resource r = f.getAnnotation(Resource.class);
+
+                    final Context jndiContext = SystemInstance.get().getComponent(ContainerSystem.class).getJNDIContext();
+                    if (r.lookup() != null && !r.lookup().isBlank()) {
+                        f.set(target, jndiContext.lookup(r.lookup()));
+                    } else {
+                        final String name = r.name();
+                        if (name != null) {
+                            final String jndi;
+                            if (name.startsWith("java:") || name.startsWith("comp/env")) {
+                                jndi = name;
+                            } else {
+                                jndi = "java:comp/env/" + name;
+
+                            }
+                            f.set(target, jndiContext.lookup(jndi));
+                        }
+                    }
+                } catch (Exception ignored) {
+                }
             }
         }
         final Class<?> superclass = aClass.getSuperclass();

```