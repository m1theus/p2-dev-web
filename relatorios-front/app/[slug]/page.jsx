'use client';

import { useState, useEffect } from 'react';
import { useParams } from 'next/navigation';
import { Button } from '@/components/ui/button';
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from '@/components/ui/table';
import { Download } from 'lucide-react';

const fetchData = async (slug) => {
  return fetch(`http://localhost:8081/api/${slug}`).then(
    async (response) => await response.json()
  );
};

const downloadFile = async (slug, type) => {
  try {
    const response = await fetch(
      `http://localhost:8081/api/${slug}/download?type=${type}`,
      {
        method: 'GET',
      }
    );

    if (!response.ok) {
      throw new Error('Failed to download file');
    }

    const blob = await response.blob();
    const url = window.URL.createObjectURL(blob);

    const a = document.createElement('a');
    a.href = url;

    a.download = `relatorio-${slug}.${type}`;
    document.body.appendChild(a);
    a.click();

    window.URL.revokeObjectURL(url);
    document.body.removeChild(a);
  } catch (error) {
    console.error('Error downloading file:', error);
  }
};

const tableHeadersMap = {
  clientes: ['CPF', 'NOME', 'TELEFONE', 'E-MAIL', 'ENDEREÇO'].map(
    (item, idx) => {
      return <TableHead key={item + (idx + 1)}>{item}</TableHead>;
    }
  ),
  funcionarios: [
    '#ID',
    'NOME',
    'TELEFONE',
    'E-MAIL',
    'ENDEREÇO',
    'SALÁRIO',
  ].map((item, idx) => {
    return <TableHead key={item + (idx + 1)}>{item}</TableHead>;
  }),
  servicos: ['#ID', 'NOME', 'VALOR'].map((item, idx) => {
    return <TableHead key={item + (idx + 1)}>{item}</TableHead>;
  }),
  agendas: [
    '#ID',
    'DESCRIÇÃO',
    'DATA',
    'SERVIÇOS',
    'CLIENTE',
    'FUNCIONÁRIO',
  ].map((item, idx) => {
    return <TableHead key={item + (idx + 1)}>{item}</TableHead>;
  }),
};

const tableDataMap = {
  clientes: (item) => (
    <TableRow key={item?.cpf}>
      <TableCell>{item?.cpf}</TableCell>
      <TableCell>{item?.nome}</TableCell>
      <TableCell>{item?.telefone}</TableCell>
      <TableCell>{item?.email}</TableCell>
      <TableCell>{`#${item?.endereco.id} - ${item?.endereco.logradouro} - ${item?.endereco.numero}`}</TableCell>
    </TableRow>
  ),
  funcionarios: (item) => (
    <TableRow key={item?.id}>
      <TableCell>{item?.id}</TableCell>
      <TableCell>{item?.nome}</TableCell>
      <TableCell>{item?.telefone}</TableCell>
      <TableCell>{item?.email}</TableCell>
      <TableCell>{`#${item?.endereco.id} - ${item?.endereco.logradouro} - ${item?.endereco.numero}`}</TableCell>
      <TableCell>{item?.salario}</TableCell>
    </TableRow>
  ),
  servicos: (item) => (
    <TableRow key={item?.id}>
      <TableCell>{item?.id}</TableCell>
      <TableCell>{item?.nome}</TableCell>
      <TableCell>{item?.valor}</TableCell>
    </TableRow>
  ),
  agendas: (item) => (
    <TableRow key={item?.id}>
      <TableCell>{item?.id}</TableCell>
      <TableCell>{item?.descricao}</TableCell>
      <TableCell>{item?.data}</TableCell>
      <TableCell>
        {item?.servicos?.map(
          (item) => `${item.id} - ${item.nome} - ${item.valor}`
        )}
      </TableCell>
      <TableCell>{`${item?.cliente?.cpf} - ${item?.cliente?.nome}`}</TableCell>
      <TableCell>{`#${item?.funcionario.id} - ${item?.funcionario.nome}`}</TableCell>
    </TableRow>
  ),
};

export default function DynamicPage() {
  const { slug } = useParams();
  const [data, setData] = useState([]);
  const [headers, setHeaders] = useState([]);

  useEffect(() => {
    fetchData(slug).then(setData);
    if (slug) {
      setHeaders(tableHeadersMap[slug]);
    }
  }, [slug]);

  const handleDownload = async (format) => {
    console.log(`Downloading ${format} for ${slug}`);
    await downloadFile(slug, format);
  };

  const generateTableData = (slug, data) => {
    if (!data) return;
    if (!slug) return;
    const table = tableDataMap[slug](data);
    return table;
  };

  return (
    <div className="space-y-6">
      <div className="flex justify-between items-center">
        <h1 className="text-3xl font-bold capitalize">{slug}</h1>
        <div className="flex space-x-2">
          <Button
            onClick={() => handleDownload('pdf')}
            variant="outline"
            size="sm"
          >
            <Download className="mr-2 h-4 w-4" /> PDF
          </Button>
          <Button
            onClick={() => handleDownload('csv')}
            variant="outline"
            size="sm"
          >
            <Download className="mr-2 h-4 w-4" /> CSV
          </Button>
          <Button
            onClick={() => handleDownload('xlsx')}
            variant="outline"
            size="sm"
          >
            <Download className="mr-2 h-4 w-4" /> XLSX
          </Button>
        </div>
      </div>
      <div className="bg-white shadow-md rounded-lg overflow-hidden">
        <Table>
          <TableHeader>
            <TableRow>{headers}</TableRow>
          </TableHeader>
          <TableBody>
            {data.map((item) => generateTableData(slug, item))}
          </TableBody>
        </Table>
      </div>
    </div>
  );
}
