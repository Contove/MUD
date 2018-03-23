CS3524_HOME="$(HOME)/mud"

RM_FLAGS="-f"

assignment:
	javac cs3524/solutions/mud/Client.java;\
	javac cs3524/solutions/mud/Edge.java;\
	javac cs3524/solutions/mud/MUD.java;\
	javac cs3524/solutions/mud/Vertex.java;\
	javac cs3524/solutions/mud/ServerMainline.java;\
	javac cs3524/solutions/mud/ServerImpl.java;\
	javac cs3524/solutions/mud/Player.java;\
	javac cs3524/solutions/mud/ServerInterface.java;\






###########################################################
## cleanall
###########################################################

cleanall:	spsclean ircclean collegeclean distcollegeclean factoryclean tpgclean ircsolnclean collegesolnclean distcollegesolnclean factorysolnclean

###########################################################
## tarall
###########################################################

tarall:	rmishouttar spstar collegetar rmishoutsolntar spssolntar collegesolntar
