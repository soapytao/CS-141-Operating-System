JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
        $(JC) $(JFLAGS) $*.java

CLASSES = \
        FileInfo.java \
        UserThread.java \
        Printer.java \
        Disk.java \
        ResourceManager.java \
        DirectoryManager.java \
        PrintJobThread.java \
        JavaFXMain.java \
        Main.java

default: clean classes exec

classes: $(CLASSES:.java=.class)

clean:
        $(RM) *.class
        $(RM) PRINTER*

exec:
        java Main