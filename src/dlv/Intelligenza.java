package dlv;

import logic.HexagonDLV;
import java.util.LinkedList;

public  class Intelligenza{

public Intelligenza(){
}
public LinkedList<HexagonDLV> solve(LinkedList<HexagonDLV> matrix){
LinkedList<HexagonDLV> result= new LinkedList<HexagonDLV>();

	// ---- START - startProgram ---- 
it.unical.mat.jdlv.program.JDLV_Logger.getInstance().logInfoMessage("Creation EXECUTING JDLV module.");
it.unical.mat.wrapper.DLVInputProgram _JDLV_PROGRAM_EXECUTING=new it.unical.mat.wrapper.DLVInputProgramImpl();
_JDLV_PROGRAM_EXECUTING.cleanText();
java.lang.StringBuffer _JDLV_PROGRAM_BUFFER_EXECUTING=new java.lang.StringBuffer();
it.unical.mat.wrapper.DLVInvocation _JDLV_INVOCATION_EXECUTING;

	// ---- END - startProgram ---- 

	// ---- START - addInMapping ---- 
_JDLV_PROGRAM_EXECUTING.addText(it.unical.mat.jdlv.program.TypeSolver.getNameTranslation(matrix,"m"));
it.unical.mat.jdlv.program.JDLV_Logger.getInstance().logInfoMessage("Add in-mapping 'matrix::m' in module EXECUTING:"+ it.unical.mat.jdlv.program.JDLV_Logger.getInstance().getPrettyCode(it.unical.mat.jdlv.program.TypeSolver.getNameTranslation(matrix,"m"), 0));

	// ---- END - addInMapping ---- 
it.unical.mat.jdlv.program.JDLV_Logger.getInstance().logInfoMessage("Add out-mapping 'result::res' in module EXECUTING.");

	// ---- START - prepareJDLVCall ---- 
try{

_JDLV_INVOCATION_EXECUTING=it.unical.mat.wrapper.DLVWrapper.getInstance().createInvocation(it.unical.mat.jdlv.util.JdlvProperties.getInstance().getDlvExecutablePath());
_JDLV_PROGRAM_EXECUTING.addText(_JDLV_PROGRAM_BUFFER_EXECUTING.toString());
_JDLV_PROGRAM_EXECUTING.addText("#maxint = 150."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("dispari(1)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("dispari(3)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("dispari(5)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("dispari(7)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("dispari(9)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("dispari(11)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("dispari(13)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("pari(2)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("pari(4)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("pari(6)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("pari(8)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("pari(10)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("pari(12)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("pari(14)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("non_res(X, Y, C, Id) v res(X, Y, C, Id) :- m(X, Y, C, Id)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("contaP(Id, N) :- m(_, _, _, Id), #count{X,Y : m(X, Y, C, Id), C != 0} = N."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("minP(R) :- #min{N : contaP(_, N)} = R."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("matriceScelta(Id, N) :- contaP(Id, N), minP(N)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("matriceSceltaId(Id) :- matriceScelta(Id, _)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText(":- res(_, _, _, Id), not matriceSceltaId(Id)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("adiacenze(X, Y, X2, Y2, C, Id) :- m(X, Y, C, Id), m(X2, Y2, C2, Id2), not Id != Id2, C != 0, not C != C2, A = X + Y, B = X2 + Y2, B = A + 1, X2 >= X, Y2 >= Y."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("adiacenze(X, Y, X2, Y2, C, Id) :- m(X, Y, C, Id), m(X2, Y2, C2, Id2), not Id != Id2, C != 0, not C != C2, A = X + Y, B = X2 + Y2, B = A - 1, X2 <= X, Y2 <= Y."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("adiacenze(X, Y, X2, Y2, C, Id) :- m(X, Y, C, Id), m(X2, Y2, C2, Id2), not Id != Id2, C != 0, not C != C2, dispari(X), X2 = X + 1, Y2 = Y + 1."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("adiacenze(X, Y, X2, Y2, C, Id) :- m(X, Y, C, Id), m(X2, Y2, C2, Id2), not Id != Id2, C != 0, not C != C2, dispari(X), X2 = X - 1, Y2 = Y + 1."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("adiacenze(X, Y, X2, Y2, C, Id) :- m(X, Y, C, Id), m(X2, Y2, C2, Id2), not Id != Id2, C != 0, not C != C2, pari(X), X2 = X - 1, Y2 = Y - 1."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("adiacenze(X, Y, X2, Y2, C, Id) :- m(X, Y, C, Id), m(X2, Y2, C2, Id2), not Id != Id2, C != 0, not C != C2, pari(X), X2 = X + 1, Y2 = Y - 1."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("pallineTris(X, Y, N, Id) :- matriceSceltaId(Id), m(X, Y, C, Id), #count{X2,Y2 : adiacenze(X, Y, X2, Y2, C, Id)} = N, N > 1."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("tris(Id, V) :- matriceSceltaId(Id), #count{X,Y : pallineTris(X, Y, N, Id)} = V."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("pallineDelTris(X, Y, Id) :- pallineTris(X, Y, _, Id)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("pallineCoppia(X, Y, Id) :- matriceSceltaId(Id), m(X, Y, C, Id), not pallineDelTris(X, Y, Id), #count{X2,Y2 : adiacenze(X, Y, X2, Y2, C, Id), not pallineDelTris(X2, Y2, Id), pari(X), Y2 >= Y} = 1."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("pallineCoppia(X, Y, Id) :- matriceSceltaId(Id), m(X, Y, C, Id), not pallineDelTris(X, Y, Id), #count{X2,Y2 : adiacenze(X, Y, X2, Y2, C, Id), not pallineDelTris(X2, Y2, Id), dispari(X), Y2 > Y} = 1."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("coppia(Id, V) :- matriceSceltaId(Id), #count{X,Y : pallineCoppia(X, Y, Id)} = V."+'\n');
_JDLV_PROGRAM_EXECUTING.addText(":- res(_, _, _, Id), res(_, _, _, Id2), Id != Id2."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("tmp(Id) :- non_res(_, _, _, Id)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("tutte(Id) :- m(_, _, _, Id)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("altezza(Id, S) :- tutte(Id), #min{X : m(X, _, C, Id), C != 0} = S."+'\n');
_JDLV_PROGRAM_EXECUTING.addText(":~ tris(Id, V), tmp(Id). [V:3]"+'\n');
_JDLV_PROGRAM_EXECUTING.addText(":~ coppia(Id, V), tmp(Id). [V:2]"+'\n');
_JDLV_PROGRAM_EXECUTING.addText(":~ altezza(Id, S), tmp(Id). [S:1]"+'\n');
_JDLV_PROGRAM_EXECUTING.getFiles().clear();
_JDLV_INVOCATION_EXECUTING.setNumberOfModels(1);
_JDLV_PROGRAM_BUFFER_EXECUTING.append("");
_JDLV_INVOCATION_EXECUTING.setInputProgram(_JDLV_PROGRAM_EXECUTING);
it.unical.mat.wrapper.ModelBufferedHandler _BUFFERED_HANDLER_EXECUTING=new it.unical.mat.wrapper.ModelBufferedHandler(_JDLV_INVOCATION_EXECUTING);
it.unical.mat.jdlv.program.JDLV_Logger.getInstance().logInfoMessage("Start execution EXECUTING program: executablePath='"+it.unical.mat.jdlv.util.JdlvProperties.getInstance().getDlvExecutablePath()+"', options='"+_JDLV_INVOCATION_EXECUTING.getOptionsString()+"'"+'\n'+"Code execution: "+it.unical.mat.jdlv.program.JDLV_Logger.getInstance().getPrettyCode(_JDLV_INVOCATION_EXECUTING.getInputProgram().getCompleteText(),0));
_JDLV_INVOCATION_EXECUTING.run();
while(_BUFFERED_HANDLER_EXECUTING.hasMoreModels()){
it.unical.mat.wrapper.Model _temporary_JDLV_MODELEXECUTING=_BUFFERED_HANDLER_EXECUTING.nextModel();
it.unical.mat.jdlv.program.TypeSolver.loadPredicate(result, "res",_temporary_JDLV_MODELEXECUTING,HexagonDLV.class);
it.unical.mat.jdlv.program.JDLV_Logger.getInstance().logInfoMessage("Process new answer_set"+ '\n' + "Results:"+ '\n'+ "result " + result.size() + " elements"+ '\n' + it.unical.mat.jdlv.program.JDLV_Logger.getInstance().getPrettyObject(result,0));
}
if(_JDLV_INVOCATION_EXECUTING.haveModel()==false){
System.out.println( "Nessuna soluzione" );
return  null ;
}
if(!_JDLV_INVOCATION_EXECUTING.getErrors().isEmpty()){
throw new java.lang.RuntimeException(_JDLV_INVOCATION_EXECUTING.getErrors().get(0).getText());
}
}
catch(java.lang.Throwable _JDLV_EXCEPTION_EXECUTING){
it.unical.mat.jdlv.program.JDLV_Logger.getInstance().logErrorMessage(_JDLV_EXCEPTION_EXECUTING.getMessage());
}
_JDLV_PROGRAM_EXECUTING.cleanText();

	// ---- END - prepareJDLVCall ---- 
return result;
}
public LinkedList<HexagonDLV> solve1(LinkedList<HexagonDLV> matrix){
LinkedList<HexagonDLV> result= new LinkedList<HexagonDLV>();

	// ---- START - startProgram ---- 
it.unical.mat.jdlv.program.JDLV_Logger.getInstance().logInfoMessage("Creation EXECUTING JDLV module.");
it.unical.mat.wrapper.DLVInputProgram _JDLV_PROGRAM_EXECUTING=new it.unical.mat.wrapper.DLVInputProgramImpl();
_JDLV_PROGRAM_EXECUTING.cleanText();
java.lang.StringBuffer _JDLV_PROGRAM_BUFFER_EXECUTING=new java.lang.StringBuffer();
it.unical.mat.wrapper.DLVInvocation _JDLV_INVOCATION_EXECUTING;

	// ---- END - startProgram ---- 

	// ---- START - addInMapping ---- 
_JDLV_PROGRAM_EXECUTING.addText(it.unical.mat.jdlv.program.TypeSolver.getNameTranslation(matrix,"m"));
it.unical.mat.jdlv.program.JDLV_Logger.getInstance().logInfoMessage("Add in-mapping 'matrix::m' in module EXECUTING:"+ it.unical.mat.jdlv.program.JDLV_Logger.getInstance().getPrettyCode(it.unical.mat.jdlv.program.TypeSolver.getNameTranslation(matrix,"m"), 0));

	// ---- END - addInMapping ---- 
it.unical.mat.jdlv.program.JDLV_Logger.getInstance().logInfoMessage("Add out-mapping 'result::res' in module EXECUTING.");

	// ---- START - prepareJDLVCall ---- 
try{

_JDLV_INVOCATION_EXECUTING=it.unical.mat.wrapper.DLVWrapper.getInstance().createInvocation(it.unical.mat.jdlv.util.JdlvProperties.getInstance().getDlvExecutablePath());
_JDLV_PROGRAM_EXECUTING.addText(_JDLV_PROGRAM_BUFFER_EXECUTING.toString());
_JDLV_PROGRAM_EXECUTING.addText("#maxint = 150."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("dispari(1)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("dispari(3)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("dispari(5)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("dispari(7)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("dispari(9)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("dispari(11)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("dispari(13)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("pari(2)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("pari(4)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("pari(6)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("pari(8)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("pari(10)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("pari(12)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("pari(14)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("non_res(X, Y, C, Id) v res(X, Y, C, Id) :- m(X, Y, C, Id)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("matriciFormate(Id, N) :- m(_, _, _, Id), #count{X,Y : m(X, Y, C, Id), C != 0} = N."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("adiacenze(X, Y, X2, Y2, C, Id) :- m(X, Y, C, Id), m(X2, Y2, C2, Id2), not Id != Id2, C != 0, not C != C2, A = X + Y, B = X2 + Y2, B = A + 1, X2 >= X, Y2 >= Y."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("adiacenze(X, Y, X2, Y2, C, Id) :- m(X, Y, C, Id), m(X2, Y2, C2, Id2), not Id != Id2, C != 0, not C != C2, A = X + Y, B = X2 + Y2, B = A - 1, X2 <= X, Y2 <= Y."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("adiacenze(X, Y, X2, Y2, C, Id) :- m(X, Y, C, Id), m(X2, Y2, C2, Id2), not Id != Id2, C != 0, not C != C2, dispari(X), X2 = X + 1, Y2 = Y + 1."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("adiacenze(X, Y, X2, Y2, C, Id) :- m(X, Y, C, Id), m(X2, Y2, C2, Id2), not Id != Id2, C != 0, not C != C2, dispari(X), X2 = X - 1, Y2 = Y + 1."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("adiacenze(X, Y, X2, Y2, C, Id) :- m(X, Y, C, Id), m(X2, Y2, C2, Id2), not Id != Id2, C != 0, not C != C2, pari(X), X2 = X - 1, Y2 = Y - 1."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("adiacenze(X, Y, X2, Y2, C, Id) :- m(X, Y, C, Id), m(X2, Y2, C2, Id2), not Id != Id2, C != 0, not C != C2, pari(X), X2 = X + 1, Y2 = Y - 1."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("pallineTris(X, Y, N, Id) :- matriciFormate(Id, _), m(X, Y, C, Id), #count{X2,Y2 : adiacenze(X, Y, X2, Y2, C, Id)} = N, N > 1."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("tris(Id, V) :- matriciFormate(Id, _), #count{X,Y : pallineTris(X, Y, N, Id)} = V."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("pallineDelTris(X, Y, Id) :- pallineTris(X, Y, _, Id)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("pallineCoppia(X, Y, Id) :- matriciFormate(Id, _), m(X, Y, C, Id), not pallineDelTris(X, Y, Id), #count{X2,Y2 : adiacenze(X, Y, X2, Y2, C, Id), not pallineDelTris(X2, Y2, Id), pari(X), Y2 >= Y} = 1."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("pallineCoppia(X, Y, Id) :- matriciFormate(Id, _), m(X, Y, C, Id), not pallineDelTris(X, Y, Id), #count{X2,Y2 : adiacenze(X, Y, X2, Y2, C, Id), not pallineDelTris(X2, Y2, Id), dispari(X), Y2 > Y} = 1."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("coppia(Id, V) :- matriciFormate(Id, _), #count{X,Y : pallineCoppia(X, Y, Id)} = V."+'\n');
_JDLV_PROGRAM_EXECUTING.addText(":- res(_, _, _, Id), res(_, _, _, Id2), Id != Id2."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("tmp(Id) :- non_res(_, _, _, Id)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("tmp2(Id) :- res(_, _, _, Id)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("tutte(Id) :- m(_, _, _, Id)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("altezza(Id, S) :- tutte(Id), #min{X : m(X, _, C, Id), C != 0} = S."+'\n');
_JDLV_PROGRAM_EXECUTING.addText(":- #count{Id : tmp2(Id)} < 1."+'\n');
_JDLV_PROGRAM_EXECUTING.addText(":~ matriciFormate(Id, N), tmp2(Id). [N:4]"+'\n');
_JDLV_PROGRAM_EXECUTING.addText(":~ tris(Id, V), tmp(Id). [V:3]"+'\n');
_JDLV_PROGRAM_EXECUTING.addText(":~ coppia(Id, V), tmp(Id). [V:2]"+'\n');
_JDLV_PROGRAM_EXECUTING.addText(":~ altezza(Id, S), tmp(Id). [S:1]"+'\n');
_JDLV_PROGRAM_EXECUTING.getFiles().clear();
_JDLV_INVOCATION_EXECUTING.setNumberOfModels(1);
_JDLV_PROGRAM_BUFFER_EXECUTING.append("");
_JDLV_INVOCATION_EXECUTING.setInputProgram(_JDLV_PROGRAM_EXECUTING);
it.unical.mat.wrapper.ModelBufferedHandler _BUFFERED_HANDLER_EXECUTING=new it.unical.mat.wrapper.ModelBufferedHandler(_JDLV_INVOCATION_EXECUTING);
it.unical.mat.jdlv.program.JDLV_Logger.getInstance().logInfoMessage("Start execution EXECUTING program: executablePath='"+it.unical.mat.jdlv.util.JdlvProperties.getInstance().getDlvExecutablePath()+"', options='"+_JDLV_INVOCATION_EXECUTING.getOptionsString()+"'"+'\n'+"Code execution: "+it.unical.mat.jdlv.program.JDLV_Logger.getInstance().getPrettyCode(_JDLV_INVOCATION_EXECUTING.getInputProgram().getCompleteText(),0));
_JDLV_INVOCATION_EXECUTING.run();
while(_BUFFERED_HANDLER_EXECUTING.hasMoreModels()){
it.unical.mat.wrapper.Model _temporary_JDLV_MODELEXECUTING=_BUFFERED_HANDLER_EXECUTING.nextModel();
it.unical.mat.jdlv.program.TypeSolver.loadPredicate(result, "res",_temporary_JDLV_MODELEXECUTING,HexagonDLV.class);
it.unical.mat.jdlv.program.JDLV_Logger.getInstance().logInfoMessage("Process new answer_set"+ '\n' + "Results:"+ '\n'+ "result " + result.size() + " elements"+ '\n' + it.unical.mat.jdlv.program.JDLV_Logger.getInstance().getPrettyObject(result,0));
}
if(_JDLV_INVOCATION_EXECUTING.haveModel()==false){
System.out.println( "Nessuna soluzione" );
return  null ;
}
if(!_JDLV_INVOCATION_EXECUTING.getErrors().isEmpty()){
throw new java.lang.RuntimeException(_JDLV_INVOCATION_EXECUTING.getErrors().get(0).getText());
}
}
catch(java.lang.Throwable _JDLV_EXCEPTION_EXECUTING){
it.unical.mat.jdlv.program.JDLV_Logger.getInstance().logErrorMessage(_JDLV_EXCEPTION_EXECUTING.getMessage());
System.err.println( "Errore" );
return  null ;
}
_JDLV_PROGRAM_EXECUTING.cleanText();

	// ---- END - prepareJDLVCall ---- 
return result;
}
public LinkedList<HexagonDLV> solve2(LinkedList<HexagonDLV> matrix){
LinkedList<HexagonDLV> result= new LinkedList<HexagonDLV>();

	// ---- START - startProgram ---- 
it.unical.mat.jdlv.program.JDLV_Logger.getInstance().logInfoMessage("Creation EXECUTING JDLV module.");
it.unical.mat.wrapper.DLVInputProgram _JDLV_PROGRAM_EXECUTING=new it.unical.mat.wrapper.DLVInputProgramImpl();
_JDLV_PROGRAM_EXECUTING.cleanText();
java.lang.StringBuffer _JDLV_PROGRAM_BUFFER_EXECUTING=new java.lang.StringBuffer();
it.unical.mat.wrapper.DLVInvocation _JDLV_INVOCATION_EXECUTING;

	// ---- END - startProgram ---- 

	// ---- START - addInMapping ---- 
_JDLV_PROGRAM_EXECUTING.addText(it.unical.mat.jdlv.program.TypeSolver.getNameTranslation(matrix,"m"));
it.unical.mat.jdlv.program.JDLV_Logger.getInstance().logInfoMessage("Add in-mapping 'matrix::m' in module EXECUTING:"+ it.unical.mat.jdlv.program.JDLV_Logger.getInstance().getPrettyCode(it.unical.mat.jdlv.program.TypeSolver.getNameTranslation(matrix,"m"), 0));

	// ---- END - addInMapping ---- 
it.unical.mat.jdlv.program.JDLV_Logger.getInstance().logInfoMessage("Add out-mapping 'result::res' in module EXECUTING.");

	// ---- START - prepareJDLVCall ---- 
try{

_JDLV_INVOCATION_EXECUTING=it.unical.mat.wrapper.DLVWrapper.getInstance().createInvocation(it.unical.mat.jdlv.util.JdlvProperties.getInstance().getDlvExecutablePath());
_JDLV_PROGRAM_EXECUTING.addText(_JDLV_PROGRAM_BUFFER_EXECUTING.toString());
_JDLV_PROGRAM_EXECUTING.addText("#maxint = 150."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("dispari(1)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("dispari(3)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("dispari(5)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("dispari(7)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("dispari(9)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("dispari(11)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("dispari(13)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("pari(2)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("pari(4)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("pari(6)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("pari(8)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("pari(10)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("pari(12)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("pari(14)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("non_res(X, Y, C, Id) v res(X, Y, C, Id) :- m(X, Y, C, Id)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("matriciFormate(Id, N) :- m(_, _, _, Id), #count{X,Y : m(X, Y, C, Id), C != 0} = N."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("adiacenze(X, Y, X2, Y2, C, Id) :- m(X, Y, C, Id), m(X2, Y2, C2, Id2), not Id != Id2, C != 0, not C != C2, A = X + Y, B = X2 + Y2, B = A + 1, X2 >= X, Y2 >= Y."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("adiacenze(X, Y, X2, Y2, C, Id) :- m(X, Y, C, Id), m(X2, Y2, C2, Id2), not Id != Id2, C != 0, not C != C2, A = X + Y, B = X2 + Y2, B = A - 1, X2 <= X, Y2 <= Y."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("adiacenze(X, Y, X2, Y2, C, Id) :- m(X, Y, C, Id), m(X2, Y2, C2, Id2), not Id != Id2, C != 0, not C != C2, dispari(X), X2 = X + 1, Y2 = Y + 1."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("adiacenze(X, Y, X2, Y2, C, Id) :- m(X, Y, C, Id), m(X2, Y2, C2, Id2), not Id != Id2, C != 0, not C != C2, dispari(X), X2 = X - 1, Y2 = Y + 1."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("adiacenze(X, Y, X2, Y2, C, Id) :- m(X, Y, C, Id), m(X2, Y2, C2, Id2), not Id != Id2, C != 0, not C != C2, pari(X), X2 = X - 1, Y2 = Y - 1."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("adiacenze(X, Y, X2, Y2, C, Id) :- m(X, Y, C, Id), m(X2, Y2, C2, Id2), not Id != Id2, C != 0, not C != C2, pari(X), X2 = X + 1, Y2 = Y - 1."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("pallineTris(X, Y, N, Id) :- matriciFormate(Id, _), m(X, Y, C, Id), #count{X2,Y2 : adiacenze(X, Y, X2, Y2, C, Id)} = N, N > 1."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("tris(Id, V) :- matriciFormate(Id, _), #count{X,Y : pallineTris(X, Y, N, Id)} = V."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("pallineDelTris(X, Y, Id) :- pallineTris(X, Y, _, Id)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("pallineCoppia(X, Y, Id) :- matriciFormate(Id, _), m(X, Y, C, Id), not pallineDelTris(X, Y, Id), #count{X2,Y2 : adiacenze(X, Y, X2, Y2, C, Id), not pallineDelTris(X2, Y2, Id), pari(X), Y2 >= Y} = 1."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("pallineCoppia(X, Y, Id) :- matriciFormate(Id, _), m(X, Y, C, Id), not pallineDelTris(X, Y, Id), #count{X2,Y2 : adiacenze(X, Y, X2, Y2, C, Id), not pallineDelTris(X2, Y2, Id), dispari(X), Y2 > Y} = 1."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("coppia(Id, V) :- matriciFormate(Id, _), #count{X,Y : pallineCoppia(X, Y, Id)} = V."+'\n');
_JDLV_PROGRAM_EXECUTING.addText(":- res(_, _, _, Id), res(_, _, _, Id2), Id != Id2."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("tmp(Id) :- non_res(_, _, _, Id)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("tmp2(Id) :- res(_, _, _, Id)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("tutte(Id) :- m(_, _, _, Id)."+'\n');
_JDLV_PROGRAM_EXECUTING.addText("altezza(Id, S) :- tutte(Id), #min{X : m(X, _, C, Id), C != 0} = S."+'\n');
_JDLV_PROGRAM_EXECUTING.addText(":- #count{Id : tmp2(Id)} < 1."+'\n');
_JDLV_PROGRAM_EXECUTING.addText(":~ altezza(Id, S), tmp(Id). [S:4]"+'\n');
_JDLV_PROGRAM_EXECUTING.addText(":~ matriciFormate(Id, N), tmp2(Id). [N:3]"+'\n');
_JDLV_PROGRAM_EXECUTING.addText(":~ tris(Id, V), tmp(Id). [V:2]"+'\n');
_JDLV_PROGRAM_EXECUTING.addText(":~ coppia(Id, V), tmp(Id). [V:1]"+'\n');
_JDLV_PROGRAM_EXECUTING.getFiles().clear();
_JDLV_INVOCATION_EXECUTING.setNumberOfModels(1);
_JDLV_PROGRAM_BUFFER_EXECUTING.append("");
_JDLV_INVOCATION_EXECUTING.setInputProgram(_JDLV_PROGRAM_EXECUTING);
it.unical.mat.wrapper.ModelBufferedHandler _BUFFERED_HANDLER_EXECUTING=new it.unical.mat.wrapper.ModelBufferedHandler(_JDLV_INVOCATION_EXECUTING);
it.unical.mat.jdlv.program.JDLV_Logger.getInstance().logInfoMessage("Start execution EXECUTING program: executablePath='"+it.unical.mat.jdlv.util.JdlvProperties.getInstance().getDlvExecutablePath()+"', options='"+_JDLV_INVOCATION_EXECUTING.getOptionsString()+"'"+'\n'+"Code execution: "+it.unical.mat.jdlv.program.JDLV_Logger.getInstance().getPrettyCode(_JDLV_INVOCATION_EXECUTING.getInputProgram().getCompleteText(),0));
_JDLV_INVOCATION_EXECUTING.run();
while(_BUFFERED_HANDLER_EXECUTING.hasMoreModels()){
it.unical.mat.wrapper.Model _temporary_JDLV_MODELEXECUTING=_BUFFERED_HANDLER_EXECUTING.nextModel();
it.unical.mat.jdlv.program.TypeSolver.loadPredicate(result, "res",_temporary_JDLV_MODELEXECUTING,HexagonDLV.class);
it.unical.mat.jdlv.program.JDLV_Logger.getInstance().logInfoMessage("Process new answer_set"+ '\n' + "Results:"+ '\n'+ "result " + result.size() + " elements"+ '\n' + it.unical.mat.jdlv.program.JDLV_Logger.getInstance().getPrettyObject(result,0));
}
if(_JDLV_INVOCATION_EXECUTING.haveModel()==false){
System.out.println( "Nessuna soluzione" );
return  null ;
}
if(!_JDLV_INVOCATION_EXECUTING.getErrors().isEmpty()){
throw new java.lang.RuntimeException(_JDLV_INVOCATION_EXECUTING.getErrors().get(0).getText());
}
}
catch(java.lang.Throwable _JDLV_EXCEPTION_EXECUTING){
it.unical.mat.jdlv.program.JDLV_Logger.getInstance().logErrorMessage(_JDLV_EXCEPTION_EXECUTING.getMessage());
System.err.println( "Errore" );
return  null ;
}
_JDLV_PROGRAM_EXECUTING.cleanText();

	// ---- END - prepareJDLVCall ---- 
return result;
}
}

