package model.statement;


import exceptions.MyException;
import model.programState.ProgramState;
import model.type.Type;
import model.utils.MyIDictionary;
import model.utils.MyIStack;
import model.value.Value;

public class VariableDeclarationStatement implements IStatement {
    String name;
    Type type;

    public VariableDeclarationStatement(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIStack<MyIDictionary<String, Value>> symbolTables = state.getSymTable();
        MyIDictionary<String, Value> symTable = symbolTables.peek();

        if (symTable.isDefined(name)) {
            throw new MyException("Variable " + name + " already exists in the symTable.");
        }
        symTable.put(name, type.defaultValue());
        state.setSymTable(symbolTables);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        typeEnv.put(name, type);
        return typeEnv;
    }

    @Override
    public IStatement deepCopy() {
        return new VariableDeclarationStatement(name, type);
    }

    @Override
    public String toString() {
        return String.format("%s %s", type.toString(), name);
    }
}