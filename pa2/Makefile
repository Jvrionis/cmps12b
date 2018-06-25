#
#			Makefile for Search.java
#			
#
#---------------------------------------------------------------------------------

JAVASRC		= Search.java	
SOURCES		= README Makefile file1 $(JARFILE)
CLASSES		= Search.class
MAINCLASS	= Search	
JARFILE		= Search

all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest
	chmod +x $(JARFILE)

$(CLASSES): $(JAVASRC)
	javac -Xlint $(JAVASRC)

clean:
	rm $(CLASSES) $(JARFILE)

