grammar C;

program:
	(varDecl ';')+ assignment
	;

varDecl
	: typeDecl declaration (',' declaration)*
	;

typeDecl
	: signName? typeName
	;

signName
	: SIGNED
	| UNSIGNED
	;

typeName
	: CHAR
	| SHORT
	| INT
	| LONG
	;

declaration
	: ID
	| ID ASSIGN number
	| ID ASSIGN MINUS number
	;

assignment
	: ID ASSIGN expression
	;

expression
    :   multiplicativeExpression
    |   expression PLUS multiplicativeExpression
    |   expression MINUS multiplicativeExpression
    ;

multiplicativeExpression
    :   castExpression
    |   multiplicativeExpression TIMES castExpression
    |   multiplicativeExpression DIVIDE castExpression
    ;

castExpression
	: typeCast expression
	| unaryExpression
	;

primaryExpression
	: ID
	| number
	| LP expression RP
	;

number
	: NUM
	| HEXNUM
	;

typeCast
	: LP typeDecl RP
	;

unaryExpression
	: primaryExpression
	| MINUS unaryExpression
	;

MINUS : '-';
ASSIGN : '=';
TIMES : '*';
DIVIDE : '/';
PLUS : '+';
LP : '(';
RP : ')';


SIGNED : 'signed';
UNSIGNED: 'unsigned';
CHAR : 'char';
SHORT : 'short';
INT : 'int';
LONG : 'long';
PUT : 'put';

fragment
ALPHA : [a-zA-Z]
      ;

fragment
DIGIT : [0-9]
      ;
fragment
UNDERSCORE : '_';

ID : ALPHA (ALPHA | DIGIT | UNDERSCORE)*
   ;

fragment
POSITIVE : [1-9]
         ;

NUM : (POSITIVE DIGIT* | '0')
    ;

HEXNUM : '0x'[0-9a-fA-F]+
	;

NEWLINE : '\r'?'\n' -> skip;

WS : [ \t]+ -> skip ;
