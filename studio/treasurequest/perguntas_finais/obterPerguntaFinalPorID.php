<?php
  $id=$_GET['id'];
  
  $conexao = mysql_connect('localhost','root','');
  
  mysql_select_db('treasurequest',$conexao);
  
  $sql="select * from perguntas_finais where id=".$id;
  
  $resultado = mysql_query($sql) or die ("Error: " . mysql_error());
  
  while($linha = mysql_fetch_object($resultado)) {
      echo $linha->id.";".$linha->dica1.";".$linha->dica2.";".$linha->dica3.";".$linha->dica4.";";
	
		$id_texto_pergunta=$linha->id_pergunta;
		$id_localizacao=$linha->id_localizacao;
		
		$sql="select * from texto_perguntas where id =".$id_texto_pergunta;
  
		$resultado = mysql_query($sql) or die ("Error: " . mysql_error());
  
		while($linha = mysql_fetch_object($resultado))
			echo $linha->pergunta.";".$linha->op1.";".$linha->op2.";".$linha->op3.";".$linha->op4.";".$linha->op5.";".$linha->resposta.";";
  
	
		$sql="select * from localizacoes where id =".$id_localizacao;
	
		$resultado = mysql_query($sql) or die ("Error: " . mysql_error());
	
		while($linha = mysql_fetch_object($resultado))
			echo $linha->x.";".$linha->y.";".$linha->dica.";".$linha->referencia;
	}
	
	
?>
