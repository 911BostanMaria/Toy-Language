package model.statement;

import exceptions.MyException;
import model.expression.IExpression;
import model.programState.ProgramState;
import model.type.Type;
import model.utils.MyDictionary;
import model.utils.MyIDictionary;
import model.utils.MyIHeap;
import model.utils.MyIProcTable;
import model.value.Value;

import java.util.List;

public class CallProcStatement implements IStatement{
    private final String procedureName;
    private final List<IExpression> expressions;

    public CallProcStatement(String procedureName, List<IExpression> expressions) {
        this.procedureName = procedureName;
        this.expressions = expressions;
    }
    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIDictionary<String, Value> symTable = state.getTopSymTable();
        MyIHeap heap = state.getHeap();
        MyIProcTable procTable = state.getProcTable();
        if (procTable.isDefined(procedureName)) {
            List<String> variables = procTable.lookUp(procedureName).getKey();
            IStatement statement = procTable.lookUp(procedureName).getValue();
            MyIDictionary<String, Value> newSymTable = new MyDictionary<>();
            for(String var: variables) {
                int ind = variables.indexOf(var);
                newSymTable.put(var, expressions.get(ind).eval(symTable, heap));
            }
            state.getSymTable().push(newSymTable);
            state.getExeStack().push(new ReturnStatement());
            state.getExeStack().push(statement);
        } else {
            throw new MyException("Procedure not defined!");
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public IStatement deepCopy() {
        return new CallProcStatement(procedureName, expressions);
    }

    @Override
    public String toString() {
        return String.format("call %s%s", procedureName, expressions.toString());
    }
}