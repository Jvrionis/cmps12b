#------------------------------------------------------------------------------------
# Makefile for lab2
#
#-------------------------------------------------------------------------------------

JAVASRC		= FileReverse.java
SOURCES		= README FileReverse.java $(JAVASRC) 
MAINCLASS	= FileReverse
CLASSES		= FileReverse.class
JARFILE		= FileReverse

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
    







