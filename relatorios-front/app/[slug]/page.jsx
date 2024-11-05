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
  return Array(10)
    .fill(null)
    .map((_, i) => ({
      id: i + 1,
      name: `${slug.charAt(0).toUpperCase() + slug.slice(1)} ${i + 1}`,
      description: `Description for ${slug} ${i + 1}`,
    }));
};

export default function DynamicPage() {
  const { slug } = useParams();
  const [data, setData] = useState([]);

  useEffect(() => {
    fetchData(slug).then(setData);
  }, [slug]);

  const handleDownload = async (format) => {
    console.log(`Downloading ${format} for ${slug}`);
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
            <TableRow>
              <TableHead>ID</TableHead>
              <TableHead>Name</TableHead>
              <TableHead>Description</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            {data.map((item) => (
              <TableRow key={item.id}>
                <TableCell>{item.id}</TableCell>
                <TableCell>{item.name}</TableCell>
                <TableCell>{item.description}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </div>
    </div>
  );
}
