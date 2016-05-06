<?php
  $nomeTime = $_GET['nomeTime'];
  
  $conexao = mysql_connect('localhost','root','');
  mysql_select_db('tesouro',$conexao);

  //retorna id do time ja cadastrado posteriormente\\
  $sql_id_time =  "SELECT id FROM `times` WHERE nome = '".$nomeTime."'";

  $result_nome_time = mysql_query($sql_id_time) or die ("Error id_time: ".mysql_error());
  $nomeT = mysql_fetch_object($result_nome_time);
  $id_time = $nomeT -> id;

  $sql="select * from jogadores where id_time=".$id_time;
  
  $resultado = mysql_query($sql) or die ("Error :" . mysql_error());

  if (mysql_num_rows($resultado) >= 4)
      echo "true";
  else
      echo "false";
?>
	
