import React from 'react'
import { Navbar, NavbarBrand, NavbarContent, NavbarItem, Link, Button } from "@nextui-org/react";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCoins } from '@fortawesome/free-solid-svg-icons';


function NavbarComp() {
    return (
        <>
            <Navbar shouldHideOnScroll isBordered className='bg-gray-300 flex justify-center'>
                <NavbarContent className="hidden sm:flex gap-4" justify="center">
                    <NavbarBrand className='flex justify-center items-cente' justify="center">
                        <Link href="/" color='foreground' className='font-semibold text-4xl text-gray-800'>
                            <FontAwesomeIcon icon={faCoins} className='mr-3' />
                            Expenses Overview
                        </Link>
                    </NavbarBrand>
                </NavbarContent>
                <NavbarContent justify="end">
                    <NavbarItem className="hidden lg:flex gap-4">
                        <Link href="/login" color='foreground' className='text-lg'>Login</Link>
                    </NavbarItem>
                    <NavbarItem>
                        <Link href="/signup" color='foreground' className='text-lg'>Sign Up</Link>
                    </NavbarItem>
                </NavbarContent>
            </Navbar>
        </>
    );
}

export default NavbarComp;