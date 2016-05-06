<?php
  $id_jogador=$_GET['id_jogador'];
  $id_pergunta= $_GET['id_pergunta'];

  $conexao = mysql_connect('localhost','root','');
  
  mysql_select_db('treasurequest',$conexao);
  
  $sql="select * from perguntas_atuais_jogadores where id_jogador=".$id_jogador;
  
  $resultado = mysql_query($sql) or die ("Error: " . mysql_error());
  
  if (mysql_num_rows($resultado) > 0) {
        $sql = "update perguntas_atuais_jogadores set id_pergunta='$id_pergunta' where id_jogador=".$id_jogador;
  
		$resultado = mysql_query($sql) or die ("Error: " . mysql_error());
  
		if($resultado)
			echo "true";
		else
			echo "false";
	}
	else {
        $sql = "insert into perguntas_atuais_jogadores (id_jogador,id_pergunta) values ('$id_jogador','$id_pergunta')";
  
		$resultado = mysql_query($sql) or die ("Error: " . mysql_error());
  
		if($resultado)
			echo "true";
		else
			echo "false";
	}
?>
