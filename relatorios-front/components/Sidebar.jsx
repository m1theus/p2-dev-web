'use client';

import Link from 'next/link';
import { usePathname } from 'next/navigation';
import { Users, Briefcase, Calendar, UserCircle } from 'lucide-react';

const menuItems = [
  { name: 'Clientes', href: '/clientes', icon: UserCircle },
  { name: 'Funcionarios', href: '/funcionarios', icon: Users },
  { name: 'Servicos', href: '/servicos', icon: Briefcase },
  { name: 'Agendas', href: '/agendas', icon: Calendar },
];

export function Sidebar() {
  const pathname = usePathname();

  return (
    <aside className="w-64 bg-gray-800 text-white p-6 overflow-y-auto">
      <nav className="space-y-4">
        {menuItems.map((item) => (
          <Link
            key={item.name}
            href={item.href}
            className={`flex items-center space-x-3 p-3 rounded-lg transition-colors ${
              pathname === item.href
                ? 'bg-gray-700 text-white'
                : 'text-gray-300 hover:bg-gray-700 hover:text-white'
            }`}
          >
            <item.icon className="h-6 w-6" />
            <span className="font-medium">{item.name}</span>
          </Link>
        ))}
      </nav>
    </aside>
  );
}
