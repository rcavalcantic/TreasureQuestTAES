<?php
  $nome =$_GET['nome'];
  $nomeTime = $_GET['nomeTime'];
  $cor = $_GET['cor'];

  $conexao = mysql_connect('localhost','root','');
  
  mysql_select_db('tesouro',$conexao);

  //retorna id do time ja cadastrado posteriormente\\
  $sql_id_time =  "SELECT id FROM `times` WHERE nome = '".$nomeTime."'";

  $result_nome_time = mysql_query($sql_id_time) or die ("Error id_time: ".mysql_error());
  $nomeT = mysql_fetch_object($result_nome_time);
  $nt = $nomeT -> id;
  
  $sql_consulta="SELECT * FROM `jogadores` WHERE nome LIKE '".$nome."' AND (id_time = ".$nt." AND cor = ".$cor.")"; //consulta jogador e cores
   
  $resultado_consulta = mysql_query($sql_consulta) or die ("Error cor: " . mysql_error());
  function resultado_consulta($resultado_consulta){ //verifica se o jogador já existe;
    if (mysql_num_rows($resultado_consulta) > 0)
        return true;
    else
        return false;
  }
  $sql_insere = "insert into jogadores (nome,id_time,cor) values ('$nome','$nt','$cor')";
 
  if( resultado_consulta($resultado_consulta) == false){// se jogador não existe, ele é cadastrado

		$resposta_insercao = mysql_query($sql_insere) or die ("Error insercao: " . mysql_error());
		echo "Jogador cadastrado com sucesso";
	}
   else
        echo "Jogador já existe";
?>