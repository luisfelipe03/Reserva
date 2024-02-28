import React from "react";

interface TemplateProps {
    children: React.ReactNode;      
}

export const Template: React.FC<TemplateProps> = (props:TemplateProps) => {
  return (
    <>
        <Header />
            <div className="container mx-auto mt-8 px-4">
                { props.children }
            </div>
        <Footer />
    </>
  );
}

const Header: React.FC = () => {
    return (
      <header className="bg-indigo-950 text-white py-3">
        <div className='conteiner mx-auto flex justify-between items-center px-4'>
          <h1 className='text-3x1 font-bold'>Reserva</h1>
        </div>
      </header>
    )
}

const Footer: React.FC = () => {
    return (
      <footer className="bg-indigo-950 text-white py-4 mt-8">
        <div className='conteiner mx-auto text-center'>
          Desenvolvido pelos macaxeiros
        </div>
      </footer>
    )   
}
