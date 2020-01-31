dbName := blog
fileName := lprodb

all: run

run:
	java -cp ../hsqldb.jar org.hsqldb.server.Server --database.0 file:${fileName} --dbname.0 ${dbName}
	
clean:
	rm -rf ${fileName}.*
	
rebuild: clean
	

