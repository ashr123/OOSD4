public interface Visitor
{
	void visit(Dart dart);
	void visit(Lava lava);
	void visit(Poison poison);
	void visit(Magician magician);
}
