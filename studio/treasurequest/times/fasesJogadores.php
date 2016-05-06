<?php
  $id_time =$_GET['id_time'];
  
  $conexao = mysql_connect('localhost','root','');
  
  mysql_select_db('treasurequest',$conexao);
  
  $sql="select * from jogadores where id_time=".$id_time." order by cor";
  
  $resultado = mysql_query($sql) or die ("Error: " . mysql_error());
  
  while($linha = mysql_fetch_object($resultado))
      echo $linha->fase.";";
?>
	
