.PHONY: clean jmtrace

.DEFAULT_GOAL := jmtrace

ASM_JAR := lib/asm-7.2.jar
CONFIG := MANIFEST.MF
JMTRACE := jmtrace.jar
JMTRACE_BIN := jmtrace
ifeq ($(V),1)
Q=
else
Q=@
endif

$(JMTRACE): $(shell find src/ -name "*.java")
	$(Q)mkdir -p output && cp $(ASM_JAR) output/$(JMTRACE)
	$(Q)javac -cp $(ASM_JAR) src/*.java -d output/
	$(Q)cd output/ && jar -ufm $(JMTRACE) ../$(CONFIG) *.class
	$(Q)cp output/$(JMTRACE) .
	$(Q)cp jmtrace /usr/bin/

jmtrace: $(JMTRACE)

clean:
	$(Q)rm -rf output
	$(Q)rm -f $(JMTRACE)

	

