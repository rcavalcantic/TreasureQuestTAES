<?php
  $jogador =$_GET['jogador'];
  $fase =$_GET['fase'];
  
  $conexao = mysql_connect('localhost','root','');
  
  mysql_select_db('treasurequest',$conexao);
  
  $sql="select * from perguntas where jogador =".$jogador." and fase =".$fase." order by rand() limit 1";
  
  $resultado = mysql_query($sql) or die ("Error: " . mysql_error());
  
  while($linha = mysql_fetch_object($resultado)) {
		$id_localizacao=$linha->id_localizacao;
		
		$sql="select * from localizacoes where id =".$id_localizacao;
	
		$resultado = mysql_query($sql) or die ("Error: " . mysql_error());
	
		while($linha = mysql_fetch_object($resultado))
			echo $linha->x.";".$linha->y.";".$linha->dica;
   }
?>
