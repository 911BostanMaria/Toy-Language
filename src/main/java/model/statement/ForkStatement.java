package model.statement;

import exceptions.MyException;
import model.programState.ProgramState;
import model.type.Type;
import model.utils.MyDictionary;
import model.utils.MyIDictionary;
import model.utils.MyIStack;
import model.utils.MyStack;
import model.value.Value;

import java.util.Map;

public class ForkStatement implements IStatement{
    private final IStatement statement;

    public ForkStatement(IStatement statement) {
        this.statement = statement;
    }
    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIStack<IStatement> newStack = new MyStack<>();
        newStack.push(statement);
        MyIStack<MyIDictionary<String, Value>> newSymTable = state.getSymTable().clone1();
        return new ProgramState(newStack, newSymTable, state.getOut(), state.getFileTable(), state.getHeap(), state.getProcTable());
    }


    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        statement.typeCheck(typeEnv.deepCopy());
        return typeEnv;
    }

    @Override
    public IStatement deepCopy() {
        return new ForkStatement(statement.deepCopy());
    }

    @Override
    public String toString() {
        return String.format("Fork(%s)", statement.toString());
    }
}